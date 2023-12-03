package twoknightsgame.state;

import org.tinylog.Logger;
import twoknightsgame.state.entity.Game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * A {@code Database} osztály felelős az adatbázis kapcsolatért.
 */
public class Database {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("twoknights");

    /**
     * A metódus biztosítja a játék végén az eredmény feltöltését az adatbázisba.
     *
     * @param game entitás, amely az adatbázisban szereplő Game táblát reprezentálja
     */
    public static void saveGameResultToDB(Game game) {
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(game);
            entityManager.getTransaction().commit();
            Logger.debug("Saved the result of the Game to the Database!");
        } finally {
            entityManager.close();
        }
    }

    /**
     * A metódus egy lekérdezést hajt végre, mely a 10 legtöbb játékot megnyert játékost adja vissza.
     *
     * @return többsoros {@code String}-ként a 10 legjobb játékos nevét
     */
    public static String getTopTen() {
        EntityManager entityManager = emf.createEntityManager();
        try {
            List<Object[]> rankQueryList = entityManager.createQuery(
                    "SELECT g.winner, COUNT(g)"
                            + "FROM Game g GROUP BY g.winner " +
                            "ORDER BY COUNT(g) DESC").setMaxResults(10).getResultList();
            Logger.debug("Made a query to get the top 10 players!");
            return validateQueryList(rankQueryList);
        } finally {
            entityManager.close();
        }
    }

    private static String validateQueryList(List<Object[]> rankQueryList) {
        StringBuilder topPlayers = new StringBuilder("TOP 10 PLAYERS\n");
        int index = 1;
        for (Object[] row : rankQueryList) {
            topPlayers.append(index).append(". ")
                    .append(row[0]).append(" ").append(row[1]).append(" winnings").append("\n");
            index++;
        }
        return topPlayers.toString();
    }

}