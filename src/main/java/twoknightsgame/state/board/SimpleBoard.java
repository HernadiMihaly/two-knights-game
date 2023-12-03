package twoknightsgame.state.board;

import org.tinylog.Logger;
import twoknightsgame.state.piece.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Egy olyan osztály amely egy egyszerű 10x10-es táblát reprezentál, amelyen a játék folyik.
 */
public class SimpleBoard implements Board {
    private final Tile[][] board = new Tile[10][10];

    public SimpleBoard() {
        initializeBoard();
        Logger.debug("The board has been created for the match!");
        Logger.info("The board contains of 10x10 tiles!");
    }

    private void initializeBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Tile(new Point(i, j), Color.EMPTY);
            }
        }
        board[0][0] = new Tile(new Point(0, 0), Color.WHITE);
        board[0][9] = new Tile(new Point(0, 9), Color.BLACK);
    }

    @Override
    public Tile[][] getCurrentBoard() {
        return board;
    }

    @Override
    public List<Point> possibleSteps(Tile tile) {
        List<Point> availablePoints = new ArrayList<>();
        List<Point> allPoints = calculateStepPoints(tile);

        for (Point point : allPoints) {
            if (isValidPoint(point) && board[point.getX()][point.getY()].getColor() == Color.EMPTY) {
                availablePoints.add(point);
            }
        }
        Logger.debug("The possible steps are calculated for {} point!", tile.getPoint());
        return availablePoints;
    }

    private boolean isValidPoint(Point point) {
        return point.getX() >= 0 && point.getY() >= 0 && point.getX() <= 9 && point.getY() <= 9;
    }

    private List<Point> calculateStepPoints(Tile tile) {
        List<Point> points = new ArrayList<>();
        int[][] offsets = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};

        for (int[] offset : offsets) {
            points.add(new Point(tile.getPoint().getX() + offset[0], tile.getPoint().getY() + offset[1]));
        }
        return points;
    }

    @Override
    public void paint(Point p, Color c) {
        board[p.getX()][p.getY()].setColor(c);
        Logger.debug("The {} tile painted to {}", p, c);
    }
}
