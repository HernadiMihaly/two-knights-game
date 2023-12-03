package twoknightsgame.state;

import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Egy olyan osztály amely egy egyszerű 10x10-es táblát reprezentál, amelyen a játék folyik.
 */
public class SimpleBoard implements Board {
    private final Tile[][] board = new Tile[10][10];

    /**
     * A {@code SimpleBoard} osztály konstruktora, amelyen keresztül egy új 10x10-es tábla készíthető,
     * mely már tartalmazza a fekete és fehér alaphelyzetét és az üres mezőket.
     */
    public SimpleBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Tile(new Point(i, j), Color.EMPTY);
            }
        }
        board[0][0] = new Tile(new Point(0, 0), Color.WHITE);
        board[0][9] = new Tile(new Point(0, 9), Color.BLACK);

        Logger.debug("The board has been created for the match!");
        Logger.info("The board contains of 10x10 tiles!");
    }

    @Override
    public Tile[][] getCurrentBoard() {
        return board;
    }

    @Override
    public List<Point> possibleSteps(Tile tile) {
        List<Point> availablePoints = new ArrayList<>();
        List<Point> allPoints = calculateStepPoints(tile);

        for (Point point1 : allPoints) {
            if (point1.getX() >= 0 && point1.getY() >= 0 && point1.getX() <= 9 && point1.getY() <= 9 && board[point1.getX()][point1.getY()].getColor() == Color.EMPTY) {
                availablePoints.add(point1);
            }
        }
        Logger.debug("The possible steps are calculated for {} point!", tile.getPoint().getX() + "," + tile.getPoint().getY());

        return availablePoints;
    }

    private List<Point> calculateStepPoints(Tile tile) {
        List<Point> points = new ArrayList<>();

        points.add(new Point(tile.getPoint().getX() - 2, tile.getPoint().getY() - 1));
        points.add(new Point(tile.getPoint().getX() - 2, tile.getPoint().getY() + 1));
        points.add(new Point(tile.getPoint().getX() - 1, tile.getPoint().getY() + 2));
        points.add(new Point(tile.getPoint().getX() + 1, tile.getPoint().getY() + 2));
        points.add(new Point(tile.getPoint().getX() + 2, tile.getPoint().getY() + 1));
        points.add(new Point(tile.getPoint().getX() + 2, tile.getPoint().getY() - 1));
        points.add(new Point(tile.getPoint().getX() + 1, tile.getPoint().getY() - 2));
        points.add(new Point(tile.getPoint().getX() - 1, tile.getPoint().getY() - 2));

        return points;
    }

    @Override
    public void Paint(Point p, Color c) {
        board[p.getX()][p.getY()].setColor(c);
        Logger.debug("The {} tile painted to {}", p.getX() + "," + p.getY(), c);
    }

}
