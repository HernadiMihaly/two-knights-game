package twoknightsgame.state.board;

import twoknightsgame.state.piece.Color;

import java.util.Objects;

/**
 *A {@code Tile} osztály egy-egy tábla mezőt reprezentáló osztály.
 */
public class Tile {
    private Color color;
    private final Point point;

    /**
     * A {@code Tile} osztály konstruktora, melyen keresztül egy-egy új tábla mező példányosítható,
     * át kell adni neki egy pontot és egy színt.
     *
     * @param point egy {@code Point}, ami az adott mező táblán belüli helyzete lesz
     * @param color egy {@code Color}, amely az adott mező színe lesz
     */
    public Tile(Point point, Color color) {
        this.color = color;
        this.point = point;
    }

    /**
     * Egy getter, amely visszaadja egy adott mező színét.
     * @return egy {@code Color}, mely az adott mező színe
     */
    public Color getColor() {
        return color;
    }

    /**
     * Egy setter, amelyen keresztül átállíthatjuk egy már létező mező színét.
     * @param color az adott szín amire a mezőt akarjuk színezni.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Egy getter, amely visszaadja az adott mező helyzetét a táblán belül.
     * @return egy {@code Point}, ahol a mező áll a táblán belül
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Két mező összehasonlítását lehetővé tevő metódus.
     *
     * @param o egy objektum amihez hasonlítjuk az adott {@code Tile} objektumot
     * @return egy logikai értéket, amely {@code true}, ha egyezik a két objektum, {@code false} ha nem
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return color == tile.color && Objects.equals(point, tile.point);
    }

    /**
     * Az objektum hashcode értékét adja vissza.
     *
     * @return egy hashcode értéket az adott objektumhoz
     */
    @Override
    public int hashCode() {
        return Objects.hash(color, point);
    }
}
