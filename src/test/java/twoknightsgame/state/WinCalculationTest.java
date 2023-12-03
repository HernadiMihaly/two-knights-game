package twoknightsgame.state;

import org.tinylog.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import twoknightsgame.state.board.Board;
import twoknightsgame.state.board.Point;
import twoknightsgame.state.board.SimpleBoard;
import twoknightsgame.state.piece.Color;
import twoknightsgame.state.util.WinCalculation;

import static org.junit.jupiter.api.Assertions.*;

class WinCalculationTest {
    Board board = new SimpleBoard();
    WinCalculation winCalculation = new WinCalculation(board);

    @BeforeAll
    static void printBeforeAll() {
        Logger.debug("The WinCalculation's test started: ");
    }

    @AfterAll
    static void printAfterAll() {
        Logger.debug("The WinCalculation's test ended.");
    }

    @Test
    void winHorizontally() {
        board.paint(new Point(9, 0), Color.BLACK);
        board.paint(new Point(9, 1), Color.BLACK);
        board.paint(new Point(9, 2), Color.BLACK);
        board.paint(new Point(9, 3), Color.BLACK);
        board.paint(new Point(9, 4), Color.BLACK);

        assertAll(
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[9][0])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[9][1])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[9][2])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[9][3])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[9][4])),
                () -> assertFalse(winCalculation.isWon(board.getCurrentBoard()[2][4]))
        );
    }

    @Test
    void winVertically() {
        board.paint(new Point(3, 2), Color.BLACK);
        board.paint(new Point(4, 2), Color.BLACK);
        board.paint(new Point(5, 2), Color.BLACK);
        board.paint(new Point(6, 2), Color.BLACK);
        board.paint(new Point(7, 2), Color.BLACK);

        assertAll(
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[3][2])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[4][2])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[5][2])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[6][2])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[7][2])),
                () -> assertFalse(winCalculation.isWon(board.getCurrentBoard()[1][7]))
        );
    }

    @Test
    void winLeftMainDiagTest() {
        board.paint(new Point(5, 5), Color.BLACK);
        board.paint(new Point(6, 6), Color.BLACK);
        board.paint(new Point(7, 7), Color.BLACK);
        board.paint(new Point(8, 8), Color.BLACK);
        board.paint(new Point(9, 9), Color.BLACK);

        assertAll(
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[5][5])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[6][6])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[7][7])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[8][8])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[9][9])),
                () -> assertFalse(winCalculation.isWon(board.getCurrentBoard()[1][7]))
        );

    }

    @Test
    void winLeftLowerDiagTest() {
        board.paint(new Point(5, 1), Color.BLACK);
        board.paint(new Point(6, 2), Color.BLACK);
        board.paint(new Point(7, 3), Color.BLACK);
        board.paint(new Point(8, 4), Color.BLACK);
        board.paint(new Point(9, 5), Color.BLACK);

        assertAll(
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[5][1])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[6][2])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[7][3])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[8][4])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[9][5])),
                () -> assertFalse(winCalculation.isWon(board.getCurrentBoard()[9][6]))
        );
    }

    @Test
    void winLeftUpperDiagTest() {
        board.paint(new Point(1, 4), Color.BLACK);
        board.paint(new Point(2, 5), Color.BLACK);
        board.paint(new Point(3, 6), Color.BLACK);
        board.paint(new Point(4, 7), Color.BLACK);
        board.paint(new Point(5, 8), Color.BLACK);

        assertAll(
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[1][4])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[2][5])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[3][6])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[4][7])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[5][8])),
                () -> assertFalse(winCalculation.isWon(board.getCurrentBoard()[3][9]))
        );
    }

    @Test
    void winRightMainDiagTest() {
        board.paint(new Point(2, 7), Color.BLACK);
        board.paint(new Point(3, 6), Color.BLACK);
        board.paint(new Point(4, 5), Color.BLACK);
        board.paint(new Point(5, 4), Color.BLACK);
        board.paint(new Point(6, 3), Color.BLACK);

        assertAll(
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[2][7])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[3][6])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[4][5])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[5][4])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[6][3])),
                () -> assertFalse(winCalculation.isWon(board.getCurrentBoard()[4][4]))
        );
    }

    @Test
    void winRightLowerDiagTest() {
        board.paint(new Point(5, 9), Color.BLACK);
        board.paint(new Point(6, 8), Color.BLACK);
        board.paint(new Point(7, 7), Color.BLACK);
        board.paint(new Point(8, 6), Color.BLACK);
        board.paint(new Point(9, 5), Color.BLACK);

        assertAll(
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[5][9])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[6][8])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[7][7])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[8][6])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[9][5])),
                () -> assertFalse(winCalculation.isWon(board.getCurrentBoard()[9][6]))
        );
    }

    @Test
    void winRightUpperDiagTest() {
        board.paint(new Point(4, 4), Color.BLACK);
        board.paint(new Point(5, 3), Color.BLACK);
        board.paint(new Point(6, 2), Color.BLACK);
        board.paint(new Point(7, 1), Color.BLACK);
        board.paint(new Point(8, 0), Color.BLACK);

        assertAll(
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[4][4])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[5][3])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[6][2])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[7][1])),
                () -> assertTrue(winCalculation.isWon(board.getCurrentBoard()[8][0])),
                () -> assertFalse(winCalculation.isWon(board.getCurrentBoard()[8][7]))
        );
    }

}