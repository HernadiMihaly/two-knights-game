package twoknightsgame.state;

import org.tinylog.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import twoknightsgame.state.board.Point;
import twoknightsgame.state.board.SimpleBoard;
import twoknightsgame.state.board.Tile;
import twoknightsgame.state.piece.Color;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleBoardTest {
    SimpleBoard board = new SimpleBoard();

    @BeforeAll
    static void printBeforeAll() {
        Logger.debug("The Board's test started: ");
    }

    @AfterAll
    static void printAfterAll() {
        Logger.debug("The Board's test ended.");
    }

    @Test
    void testPossibleSteps1() {
        Tile stepFrom = new Tile(new Point(2, 2), Color.WHITE);
        List<Point> calculatedSteps = board.possibleSteps(stepFrom);

        List<Point> expectedSteps = new ArrayList<>();
        expectedSteps.add(new Point(0, 1));
        expectedSteps.add(new Point(0, 3));
        expectedSteps.add(new Point(1, 4));
        expectedSteps.add(new Point(3, 4));
        expectedSteps.add(new Point(4, 3));
        expectedSteps.add(new Point(4, 1));
        expectedSteps.add(new Point(3, 0));
        expectedSteps.add(new Point(1, 0));


        assertEquals(expectedSteps, calculatedSteps);
    }

    @Test
    void testPossibleSteps2() {
        Tile stepFrom = new Tile(new Point(9, 6), Color.WHITE);
        List<Point> calculatedSteps = board.possibleSteps(stepFrom);

        List<Point> expectedSteps = new ArrayList<>();
        expectedSteps.add(new Point(7, 5));
        expectedSteps.add(new Point(7, 7));
        expectedSteps.add(new Point(8, 8));
        expectedSteps.add(new Point(8, 4));

        assertEquals(expectedSteps, calculatedSteps);
    }

    @Test
    void testPossibleSteps3() {
        Tile stepFrom = new Tile(new Point(9, 0), Color.WHITE);
        List<Point> calculatedSteps = board.possibleSteps(stepFrom);

        List<Point> expectedSteps = new ArrayList<>();
        expectedSteps.add(new Point(7, 1));
        expectedSteps.add(new Point(8, 2));

        assertEquals(expectedSteps, calculatedSteps);
    }

    @Test
    void testPaint1() {
        board.paint(new Point(1, 1), Color.WHITE);
        Color paintedColor = board.getCurrentBoard()[1][1].getColor();

        Color expectedColor = Color.WHITE;

        assertEquals(expectedColor, paintedColor);
    }

    @Test
    void testPaint2() {
        board.paint(new Point(4, 1), Color.EMPTY);
        Color paintedColor = board.getCurrentBoard()[4][1].getColor();

        Color expectedColor = Color.EMPTY;

        assertEquals(expectedColor, paintedColor);
    }

    @Test
    void testPaint3() {
        board.paint(new Point(6, 9), Color.BLACK);
        Color paintedColor = board.getCurrentBoard()[6][9].getColor();

        Color expectedColor = Color.BLACK;

        assertEquals(expectedColor, paintedColor);
    }

}