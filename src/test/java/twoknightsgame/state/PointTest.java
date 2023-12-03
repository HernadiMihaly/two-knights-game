package twoknightsgame.state;

import org.tinylog.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import twoknightsgame.state.board.Point;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    Point point = new Point(0, 0);

    @BeforeAll
    static void printBeforeAll() {
        Logger.debug("The Point's test started: ");
    }

    @AfterAll
    static void printAfterAll() {
        Logger.debug("The Point's test ended.");
    }

    @Test
    void getXTest() {
        int actualValue = point.getX();

        int expectedValue = 0;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void setXTest() {
        point.setX(5);
        int actualValue = point.getX();

        int expectedValue = 5;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getYTest() {
        int actualValue = point.getY();

        int expectedValue = 0;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void setYTest() {
        point.setY(2);
        int actualValue = point.getY();

        int expectedValue = 2;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testEquals() {
        Point point2 = new Point(0, 0);

        assertTrue(point.equals(point2));
    }

    @Test
    void testHashCode() {
        assertTrue(point.hashCode() == point.hashCode());
        assertEquals(new Point(0, 0).hashCode(), point.hashCode());
    }
}