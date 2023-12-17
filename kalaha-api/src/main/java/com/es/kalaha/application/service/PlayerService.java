package com.es.kalaha.application.service;

import com.es.kalaha.application.dto.PlayerDto;
import com.es.kalaha.domain.entities.Board;
import com.es.kalaha.domain.entities.Game;
import com.es.kalaha.domain.entities.Player;
import com.es.kalaha.infrastructure.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService {


    @Autowired
    private PlayerRepository repository;

    public PlayerService(PlayerRepository repository)
    {
        this.repository = repository;
    }

    public Player create(String userName) {
        var player = new Player();
        player.name = userName;
        repository.save(player);
        return player;
    }

    public List<Player> getAll() {
        var players = repository.findAll();
        return players;
    }

    public Player getById(UUID playerId) {
        return repository.getReferenceById(playerId);
    }


    public Player getFromGameById(Game game, UUID playerId) {
        var playerStream = game.players.stream().filter(h -> h.id.equals(playerId)).findFirst();
        if(playerStream.isEmpty()) throw  new IllegalArgumentException("There was an error trying to find the player");
        return playerStream.get();
    }
}
