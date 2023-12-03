package twoknightsgame.state;

import org.junit.jupiter.api.Test;
import twoknightsgame.state.board.Point;
import twoknightsgame.state.board.Tile;
import twoknightsgame.state.piece.Color;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    Tile tile = new Tile(new Point(0, 9), Color.WHITE);

    @Test
    void getColorTest() {
        Color actualColor = tile.getColor();

        Color expectedColor = Color.WHITE;

        assertEquals(expectedColor, actualColor);
    }

    @Test
    void setColorTest() {
        tile.setColor(Color.BLACK);
        Color actualColor = tile.getColor();

        Color expectedColor = Color.BLACK;

        assertEquals(expectedColor, actualColor);
    }

    @Test
    void getPoint() {
        Point actualPoint = tile.getPoint();

        Point expectedPoint = new Point(0, 9);

        assertEquals(expectedPoint, actualPoint);
    }

    @Test
    void testEquals() {
        Tile tile2 = new Tile(new Point(0, 9), Color.WHITE);

        assertTrue(tile2.equals(tile));
    }

    @Test
    void testHashCode() {
        assertTrue(tile.hashCode() == tile.hashCode());
        assertEquals(new Tile(new Point(0, 9), Color.WHITE).hashCode(), tile.hashCode());
    }
}