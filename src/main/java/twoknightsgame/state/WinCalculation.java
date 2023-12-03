package twoknightsgame.state;

import org.tinylog.Logger;

import static java.lang.Math.abs;

/**
 * A {@code WinCalculation} osztály a nyerés kiszámításához szükséges műveleteket biztosítja.
 */
public class WinCalculation {
    private final Board chessBoard;

    /**
     * A {@code WinCalculation} osztály konstruktora, amelyen keresztül példányosítható,
     * át kell adni neki a táblát amin játszunk.
     *
     * @param chessBoard a tábla ({@code Board}) amelyen a játék folyik
     */
    public WinCalculation(Board chessBoard) {
        this.chessBoard = chessBoard;
    }

    /**
     * Egy metódus amely visszaadja, hogy nyertünk-e egy adott mezőn.
     *
     * @param tile a mező amely alapján kiszámítódik, hogy nyertünk-e
     * @return egy logikai értéket, amely {@code true}, ha nyertünk, és {@code false} ha nem
     */
    public boolean isWon(Tile tile) {
        Logger.debug("Checking if we've won at point {}", tile.getPoint().getX() + "," + tile.getPoint().getY());
        return isHorizontalWin(tile) || isVerticalWin(tile) || isDiagonalWin(tile);
    }

    private boolean isHorizontalWin(Tile tile) {
        int counter = 0;
        for (int k = 1; k < 10; k++) {
            if (!chessBoard.getCurrentBoard()[tile.getPoint().getX()][k].getColor().equals(Color.EMPTY) &&
                    chessBoard.getCurrentBoard()[tile.getPoint().getX()][k - 1].getColor()
                            .equals(chessBoard.getCurrentBoard()[tile.getPoint().getX()][k].getColor())) {
                counter++;
                if (counter == 4) {
                    return true;
                }
            } else {
                counter = 0;
            }
        }

        return false;
    }

    private boolean isVerticalWin(Tile tile) {
        int counter = 0;
        for (int k = 1; k < 10; k++) {
            if (!chessBoard.getCurrentBoard()[k][tile.getPoint().getY()].getColor().equals(Color.EMPTY) &&
                    chessBoard.getCurrentBoard()[k - 1][tile.getPoint().getY()].getColor()
                            .equals(chessBoard.getCurrentBoard()[k][tile.getPoint().getY()].getColor())) {
                counter++;
                if (counter == 4) {
                    return true;
                }
            } else {
                counter = 0;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(Tile tile) {
        return isLeftDiagonalWin(tile) || rightDiagonalWin(tile);
    }

    private boolean isLeftDiagonalWin(Tile tile) {
        int leftDiagCounter = 0;

        Point start = new Point(0, 0);
        Point end = new Point(0, 0);

        chooseWhichLeftMatrix(start, end, tile);
        int distance = end.getX() - start.getX();

        return calculateLeftDiagWin(leftDiagCounter, start, distance);

    }

    private boolean calculateLeftDiagWin(int leftDiagCounter, Point start, int distance) {
        for (int k = 0; k < distance; k++) {
            if (!chessBoard.getCurrentBoard()[start.getX() + k + 1][start.getY() + k + 1].getColor().equals(Color.EMPTY)
                    && chessBoard.getCurrentBoard()[start.getX() + k][start.getY() + k].getColor()
                    .equals(chessBoard.getCurrentBoard()[start.getX() + k + 1][start.getY() + k + 1].getColor())) {
                leftDiagCounter++;
                if (leftDiagCounter == 4) {
                    return true;
                }
            } else {
                leftDiagCounter = 0;
            }
        }
        return false;
    }

    private void chooseWhichLeftMatrix(Point start, Point end, Tile tile) {
        if (tile.getPoint().getX() > tile.getPoint().getY()) {
            setLeftLowTriMatrixPoints(start, end, tile);
        }
        if (tile.getPoint().getX() < tile.getPoint().getY()) {
            setLeftUpTriMatrixPoints(start, end, tile);
        }
        if (tile.getPoint().getX() == tile.getPoint().getY()) {
            setLeftMainDiagPoints(start, end);
        }
    }

    private void setLeftLowTriMatrixPoints(Point start, Point end, Tile tile) {
        start.setX(tile.getPoint().getX() - tile.getPoint().getY());
        start.setY(0);
        end.setX(9);
        end.setY(9 - start.getX());
    }

    private void setLeftUpTriMatrixPoints(Point start, Point end, Tile tile) {
        start.setX(0);
        start.setY(tile.getPoint().getY() - tile.getPoint().getX());
        end.setY(9);
        end.setX(9 - start.getY());
    }

    private void setLeftMainDiagPoints(Point start, Point end) {
        start.setX(0);
        start.setY(0);
        end.setX(9);
        end.setY(9);
    }

    private boolean rightDiagonalWin(Tile tile) {
        int rightDiagCounter = 0;
        Point start = new Point(0, 0);
        Point end = new Point(0, 0);

        chooseWhichRightMatrix(start, end, tile);
        int distance = abs(start.getX() - end.getX());

        return calculateRightDiagWin(rightDiagCounter, start, distance);

    }

    private boolean calculateRightDiagWin(int rightDiagCounter, Point start, int distance) {
        for (int k = 0; k < distance; k++) {
            if (!chessBoard.getCurrentBoard()[start.getX() + k + 1][start.getY() - k - 1].getColor().equals(Color.EMPTY)
                    && chessBoard.getCurrentBoard()[start.getX() + k][start.getY() - k].getColor()
                    .equals(chessBoard.getCurrentBoard()[start.getX() + k + 1][start.getY() - k - 1].getColor())) {
                rightDiagCounter++;
                if (rightDiagCounter == 4) {
                    return true;
                }
            } else {
                rightDiagCounter = 0;
            }
        }
        return false;
    }

    private void chooseWhichRightMatrix(Point start, Point end, Tile tile) {
        if (tile.getPoint().getX() + tile.getPoint().getY() >= 9) {
            setRightLowTriMatrixPoints(start, end, tile);
        }
        if (tile.getPoint().getX() + tile.getPoint().getY() < 9) {
            setRightUpTriMatrixPoints(start, end, tile);
        }
    }

    //nincs Main diagonal, mivel ebben az esetben ez már a Low-al is számolható
    private void setRightLowTriMatrixPoints(Point start, Point end, Tile tile) {
        if (tile.getPoint().getX() == 9 && tile.getPoint().getY() == 9) {
            start.setX(9);
            start.setY(9);
            end.setX(9);
            end.setY(9);
        } else {
            start.setX((tile.getPoint().getX() + tile.getPoint().getY()) % 9);
            start.setY(tile.getPoint().getX() + tile.getPoint().getY() - start.getX());
            end.setX(start.getY());
            end.setY(start.getX());
        }
    }

    private void setRightUpTriMatrixPoints(Point start, Point end, Tile tile) {
        end.setX((tile.getPoint().getX() + tile.getPoint().getY()) % 9);
        end.setY(tile.getPoint().getX() + tile.getPoint().getY() - end.getX());
        start.setX(end.getY());
        start.setY(end.getX());
    }
}