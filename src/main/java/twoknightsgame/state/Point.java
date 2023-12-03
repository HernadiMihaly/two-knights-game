package twoknightsgame.state;

import java.util.Objects;

/**
 * A {@code Point} osztály egy X és Y koordinátákból álló kétdimenziós pontot reprezentál,
 * mely a tábla mezőinek eléréséhez használatos.
 */
public class Point {
    private int x, y;

    /**
     * A {@code Point} osztály konstruktora, melyen keresztül egy új pont példányosítható,
     * meg kell adni neki az X és Y koordinátákat.
     *
     * @param x egy {@code int} koordináta érték
     * @param y egy {@code int} koordináta érték
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Egy getter ami visszaadja az X koordináta értékét.
     *
     * @return egy {@code int} amely az X koordináta értéke
     */
    public int getX() {
        return x;
    }

    /**
     * Egy setter amellyel beállíthatjuk az X koordináta értékét.
     *
     * @param x egy {@code int} amit az X koordináta felvesz értékül
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Egy getter ami visszaadja az Y koordináta értékét.
     *
     * @return egy {@code int} amely az Y koordináta értéke
     */
    public int getY() {
        return y;
    }

    /**
     * Egy setter amellyel beállíthatjuk az Y koordináta értékét.
     *
     * @param y egy {@code int} amit az Y koordináta felvesz értékül
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Két pont összehasonlítását lehetővé tevő metódus.
     *
     * @param o egy objektum amihez hasonlítjuk az adott {@code Point} objektumot
     * @return egy logikai értéket, amely {@code true}, ha egyezik a két objektum, {@code false} ha nem
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    /**
     * Az objektum hashcode értékét adja vissza.
     *
     * @return egy hashcode értéket az adott objektumhoz
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
