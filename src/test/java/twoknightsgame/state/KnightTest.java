package twoknightsgame.state;

import org.tinylog.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import twoknightsgame.state.board.Point;
import twoknightsgame.state.board.SimpleBoard;
import twoknightsgame.state.board.Tile;
import twoknightsgame.state.piece.Color;
import twoknightsgame.state.piece.Knight;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    Knight knight = new Knight(new SimpleBoard());

    @BeforeAll
    static void printBeforeAll() {
        Logger.debug("The Knight's test started: ");
    }

    @AfterAll
    static void printAfterAll() {
        Logger.debug("The Knight's test ended.");
    }

    @Test
    void whiteMoveTest1() {
        knight.whiteMove(new Point(1, 2));
        Tile movedTile = knight.getChessBoard().getCurrentBoard()[1][2];

        Tile expectedTile = new Tile(new Point(1, 2), Color.WHITE);

        assertEquals(expectedTile, movedTile);
        assertFalse(knight.isWhiteTurn());
        assertFalse(knight.isGameOver());
        assertFalse(knight.isWhiteWon());
    }

    @Test
    void whiteMoveTest2() {
        knight.whiteMove(new Point(4, 4));
        Tile movedTile = knight.getChessBoard().getCurrentBoard()[4][4];

        Tile expectedTile = new Tile(new Point(4, 4), Color.WHITE);

        assertEquals(expectedTile, movedTile);
        assertFalse(knight.isWhiteTurn());
        assertFalse(knight.isGameOver());
        assertFalse(knight.isWhiteWon());
    }

    @Test
    void blackMoveTest1() {
        knight.blackMove(new Point(7, 2));
        Tile movedTile = knight.getChessBoard().getCurrentBoard()[7][2];

        Tile expectedTile = new Tile(new Point(7, 2), Color.BLACK);

        assertEquals(expectedTile, movedTile);
        assertTrue(knight.isWhiteTurn());
        assertFalse(knight.isGameOver());
        assertFalse(knight.isBlackWon());
    }

    @Test
    void blackMoveTest2() {
        knight.blackMove(new Point(3, 8));
        Tile movedTile = knight.getChessBoard().getCurrentBoard()[3][8];

        Tile expectedTile = new Tile(new Point(3, 8), Color.BLACK);

        assertEquals(expectedTile, movedTile);
        assertTrue(knight.isWhiteTurn());
        assertFalse(knight.isGameOver());
        assertFalse(knight.isBlackWon());
    }

    @Test
    void someStepsTest() {
        Tile actualTile = knight.stepFrom();
        Tile expectedTile = new Tile(new Point(0, 0), Color.WHITE);

        assertEquals(expectedTile, actualTile);

        knight.move(new Point(2, 1));
        actualTile = knight.stepFrom();
        expectedTile = new Tile(new Point(0, 9), Color.BLACK);

        assertEquals(expectedTile, actualTile);

        knight.move(new Point(2, 8));
        actualTile = knight.stepFrom();
        expectedTile = new Tile(new Point(2, 1), Color.WHITE);

        assertEquals(expectedTile, actualTile);
    }

    @Test
    void validXCoordinateTest() {
        knight.move(new Point(-5, 8)); //Not a valid point to move (x<0), should be ignored
        Tile actualTile = knight.stepFrom();
        Tile expectedTile = new Tile(new Point(0, 0), Color.WHITE);

        assertEquals(expectedTile, actualTile);

        knight.move(new Point(10, 8)); //Not a valid point to move (x>9), should be ignored
        actualTile = knight.stepFrom();
        expectedTile = new Tile(new Point(0, 0), Color.WHITE);

        assertEquals(expectedTile, actualTile);

        knight.move(new Point(0, 2)); //It's a valid point (x>=0 & x<=9), should switch to Black's step
        actualTile = knight.stepFrom();
        expectedTile = new Tile(new Point(0, 9), Color.BLACK);

        assertEquals(expectedTile, actualTile);
    }

    @Test
    void validYCoordinateTest() {
        knight.move(new Point(2, -1)); //Not a valid point to move (y<0), should be ignored
        Tile actualTile = knight.stepFrom();
        Tile expectedTile = new Tile(new Point(0, 0), Color.WHITE);

        assertEquals(expectedTile, actualTile);

        knight.move(new Point(2, 10)); //Not a valid point to move (y>9), should be ignored
        actualTile = knight.stepFrom();
        expectedTile = new Tile(new Point(0, 0), Color.WHITE);

        assertEquals(expectedTile, actualTile);

        knight.move(new Point(9, 0)); //It's a valid point (x>=0 & x<=9), should switch to Black's step
        actualTile = knight.stepFrom();
        expectedTile = new Tile(new Point(0, 9), Color.BLACK);

        assertEquals(expectedTile, actualTile);
    }

    @Test
    void shouldNotMoveIfWhiteWon() {
        for (int i = 1; i < 5; i++) {
            knight.whiteMove(new Point(i, i));
        }
        knight.move(new Point(9, 9));
        Tile actualTile = knight.getChessBoard().getCurrentBoard()[9][9];

        Tile expectedTile = new Tile(new Point(9, 9), Color.EMPTY);

        assertTrue(knight.isWhiteWon());
        assertTrue(knight.isGameOver());
        assertEquals(expectedTile, actualTile);
    }

    @Test
    void shouldNotMoveIfBlackWon() {
        for (int i = 1; i < 5; i++) {
            knight.blackMove(new Point(i, 9));
        }
        knight.move(new Point(5, 7));
        Tile actualTile = knight.getChessBoard().getCurrentBoard()[5][7];

        Tile expectedTile = new Tile(new Point(5, 7), Color.EMPTY);

        assertTrue(knight.isBlackWon());
        assertTrue(knight.isGameOver());
        assertEquals(expectedTile, actualTile);
    }
}