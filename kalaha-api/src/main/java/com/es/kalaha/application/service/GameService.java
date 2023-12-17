package com.es.kalaha.application.service;

import com.es.kalaha.domain.entities.*;
import com.es.kalaha.infrastructure.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.FindException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    public Game create(Board board, ArrayList<Player> players, Player currentPlayer) {
        var game = new Game();
        game.board = board;
        game.gameStatus = GameStatus.ACTIVE;
        game.players = players;
        game.currentPlayer = currentPlayer;
        repository.save(game);
        return game;
    }

    public Game getById(UUID gameId) {
        var game = repository.findById(gameId);
        if (game.isEmpty()) throw new FindException("Could not find game");
        return game.get();
    }

    public Boolean gameFinished(Game game) {
        var board = game.board;
        var playerHouses = board.houses.stream().collect(Collectors.groupingBy(House::getOwner));
        boolean gameFinished = false;
        for (Map.Entry<Player, List<House>> entry : playerHouses.entrySet()) {
            gameFinished = entry.getValue().stream().allMatch(x -> x.seeds == 0);
        }
        return gameFinished;
    }

    public Player decideCapturingPlayer(Game game) {
        var board = game.board;
        var playerHouses = board.houses.stream().collect(Collectors.groupingBy(House::getOwner));
        Player capturingPlayer = null;
        for (Map.Entry<Player, List<House>> entry : playerHouses.entrySet()) {
            var playerIsCapturing = entry.getValue().stream().anyMatch(x -> x.seeds > 0);
            if (playerIsCapturing) {
                capturingPlayer = entry.getKey();
            }
        }
        if (capturingPlayer == null) throw new IllegalArgumentException("There are no capturing players");
        return capturingPlayer;
    }


    public GameStatus getGameStatus(Game game) {
        var stores = game.board.stores;
        var seedCount = 0;
        for (var store : stores) {
            if(store.seeds == seedCount && seedCount != 0)
            {
                return GameStatus.DRAW;
            }
            seedCount = store.seeds;
        }
        return GameStatus.PLAYER_WON;
    }

    public Player decideWinner(Game game) {
        var stores = game.board.stores;
        Player winningPlayer = null;
        var seedCount = 0;
        for (var store : stores) {
            if(store.seeds > seedCount)
            {
                winningPlayer = store.owner;
            }
            seedCount = store.seeds;
        }
        return winningPlayer;
    }

    public Player getNextPlayer(Game game, Pit lastPit, Player player) {
        var stores = game.board.stores;
        var lastPitIsStore = stores.stream().anyMatch(x->x.id.equals(lastPit.id));
        if(lastPitIsStore && lastPit.owner == player)
        {
            return player;
        }
        var opponent = game.players.stream().filter(x->x.id != player.id).findAny();
        if(opponent.isEmpty())
        {
            throw new IllegalArgumentException("No opponent has been found");
        }
        return opponent.get();
    }

    public void save(Game game) {
        repository.save(game);
    }
}
