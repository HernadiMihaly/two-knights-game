package twoknightsgame.state;

import org.tinylog.Logger;

/**
 * A {@code Knight} osztály biztosítja a műveleteket a lovak mozgatásához.
 */
public class Knight {
    private final Board chessBoard;
    private final WinCalculation winCalculation;
    private Point whiteKnight = new Point(0, 0), blackKnight = new Point(0, 9);

    private boolean isWhiteTurn = true;
    private boolean isGameOver = false;
    private boolean isWhiteWon = false;
    private boolean isBlackWon = false;

    /**
     * A {@code Knight} osztály konstruktora, melyen keresztül példányosítható,
     * át kell adni neki a táblát amivel játszunk.
     *
     * @param chessBoard az a tábla amelyen a játék történik.
     */
    public Knight(Board chessBoard) {
        this.chessBoard = chessBoard;
        this.winCalculation = new WinCalculation(chessBoard);
    }

    /**
     * Egy getter metódus, amelyen keresztül el tudjuk érni az osztályból a {@code Board} metódusait.
     *
     * @return egy {@link Board} objektumot (játék tábla), amely éppen használatban van az osztály által
     */
    public Board getChessBoard() {
        return chessBoard;
    }

    /**
     * A metódus megmondja, hogy a fehér, vagy a fekete ló jön-e éppen.
     *
     * @return egy logikai értéket, amely {@code true}, ha a fehér jön, {@code false} ha a fekete
     */
    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    /**
     * A metódus megmondja, hogy győzött-e a fehér ló.
     *
     * @return egy logikai értéket, amely {@code true}, ha győzött a fehér, {@code false} ha nem
     */
    public boolean isWhiteWon() {
        return isWhiteWon;
    }

    /**
     * A metódus megmondja, hogy győzött-e a fekete ló.
     *
     * @return egy logikai értéket, amely {@code true}, ha győzött a fekete, {@code false} ha nem
     */
    public boolean isBlackWon() {
        return isBlackWon;
    }

    /**
     * A metódus megmondja, hogy vége van-e a játéknak.
     *
     * @return egy logikai értéket, amely {@code true}, ha vége a játéknak, {@code false} ha nem
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * A lovak mozgását biztosítja egy bizonyos pontra a táblán.
     *
     * @param to egy {@link Point}, amelyhez tartozó mezőre mozdulni akarunk a táblán
     */
    public void move(Point to) {
        if (!isGameOver) {
            legalMove(to);
        }
    }

    private void legalMove(Point to) {
        if (to.getX() >= 0 && to.getY() >= 0 && to.getX() <= 9 && to.getY() <= 9) {
            if (isWhiteTurn) {
                whiteMove(to);
            } else {
                blackMove(to);
            }
        }
    }

    /**
     * A metódus a fehér ló mozgását biztosítja, valamint megnézi, hogy nyert-e a lépéssel,
     * akkor hívódik, amikor a fehéren a sor.
     *
     * @param to egy {@link Point}, amelyhez tartozó mezőre mozdulni akar a fehér a táblán
     */
    public void whiteMove(Point to) {
        whiteKnight = to;
        Logger.debug("{} moved to {} point", Color.WHITE, to.getX() + "," + to.getY());
        chessBoard.Paint(whiteKnight, Color.WHITE);
        if (winCalculation.isWon(chessBoard.getCurrentBoard()[whiteKnight.getX()][whiteKnight.getY()])) {
            isGameOver = true;
            isWhiteWon = true;
        } else {
            isWhiteTurn = false;
        }
    }

    /**
     * A metódus a fekete ló mozgását biztosítja, valamint megnézi, hogy nyert-e a lépéssel,
     * akkor hívódik, amikor a feketén a sor.
     *
     * @param to egy {@link Point}, amelyhez tartozó mezőre mozdulni akar a fekete a táblán
     */
    public void blackMove(Point to) {
        blackKnight = to;
        Logger.debug("{} moved to {} point", Color.BLACK, to.getX() + "," + to.getY());
        chessBoard.Paint(blackKnight, Color.BLACK);
        if (winCalculation.isWon(chessBoard.getCurrentBoard()[blackKnight.getX()][blackKnight.getY()])) {
            isGameOver = true;
            isBlackWon = true;
        } else {
            isWhiteTurn = true;
        }
    }

    /**
     * Visszaadja azt a mezőt, amelyen éppen az a ló áll, amelyik következik.
     *
     * @return {@link Tile}-t (mező), amelyen áll a ló
     */
    public Tile stepFrom() {
        if (isWhiteTurn) {
            return chessBoard.getCurrentBoard()[whiteKnight.getX()][whiteKnight.getY()];
        }
        return chessBoard.getCurrentBoard()[blackKnight.getX()][blackKnight.getY()];
    }

}