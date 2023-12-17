package com.es.kalaha.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Data
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Player> players;
    @ManyToOne
    @JoinColumn(name = "board")
    public Board board;
    public GameStatus gameStatus;
    @ManyToOne
    public Player currentPlayer;

}
