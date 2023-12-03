package twoknightsgame.state;

import java.util.List;

/**
 * A {@code Board} interfész a játék táblájának/működésének biztosításához szükséges metódusokat tartalmazza.
 */
public interface Board {
    /**
     * A metódus felelős azon pontok kiszámolásáért ahova egy adott mezőből léphetünk a táblán.
     *
     * @param tile Az adott mező ahonnan lépni akarunk, ez alapján számítódnak ki a lehetséges lépési pontok
     * @return egy lista, amely olyan pontokból áll, melyekre szabad lépni az adott <code>tile</code>-ból
     */
    List<Point> possibleSteps(Tile tile);

    /**
     * A metódus egy adott ponthoz tartozó tábla mezőn, egy adott színt állít be.
     *
     * @param p A pont amelyhez tartozó mezőt a táblán be akarjuk színezni
     * @param c A szín amelyre az adott tábla mezőt be akarjuk színezni
     */
    void Paint(Point p, Color c);

    /**
     * A jelenlegi tábla állapotát tükröző metódus, egy getter amely visszaadja a táblát.
     *
     * @return egy {@code Tile} tömb, amely lényegében a jelenlegi állapotát mutatja a táblának
     */
    Tile[][] getCurrentBoard();
}
