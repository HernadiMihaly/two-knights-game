package twoknightsgame.state.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * A {@code Game} osztály az adatbázis Game táblájának kódban szereplő leképezése,
 * melybe a játék eredmények (nyertes és vesztes neve) kerülnek feltöltésre.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Game")
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "WINNER_NAME")
    private String winner;

    @Column(name = "LOSER_NAME")
    private String loser;

}
