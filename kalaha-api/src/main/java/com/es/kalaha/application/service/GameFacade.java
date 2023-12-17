package com.es.kalaha.application.service;

import com.es.kalaha.application.dto.*;
import com.es.kalaha.application.mapper.BoardMapper;
import com.es.kalaha.application.mapper.GameMapper;
import com.es.kalaha.application.mapper.PlayerMapper;
import com.es.kalaha.domain.entities.GameStatus;
import com.es.kalaha.domain.entities.Player;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameFacade {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private PitService pitService;
    @Autowired
    private GameService gameService;
    @Autowired
    GameMapper gameMapper;
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    PlayerMapper playerMapper;


    public GameDto createGame(CreateGameWithSettingsRequest request) {
        var player1 = playerService.getById(request.players.getFirst().id);
        var player2 = playerService.getById(request.players.getLast().id);

        var houseListForPlayer1 = pitService.createHouseForPlayer(request.seedCount, player1);
        var houseListForPlayer2 = pitService.createHouseForPlayer(request.seedCount, player2);
        var storeForPlayer1 = pitService.createStoreForPlayer(player1);
        var storeForPlayer2 = pitService.createStoreForPlayer(player2);

        pitService.SetOpposites(houseListForPlayer1, houseListForPlayer2);
        pitService.SetCircular(houseListForPlayer1, storeForPlayer1, houseListForPlayer2, storeForPlayer2);

        pitService.saveAllHouses(houseListForPlayer1);
        pitService.saveAllHouses(houseListForPlayer2);

        pitService.saveAllStores(storeForPlayer1,storeForPlayer2);

        var board = boardService.create(houseListForPlayer1, storeForPlayer1, houseListForPlayer2, storeForPlayer2);

        var playerList = new ArrayList<Player>();
        playerList.add(player1);
        playerList.add(player2);

        var game = gameService.create(board, playerList, player1);
        var dto = gameMapper.mapFromGame(game);
        dto.players = request.players;

        var foo = new Gson().toJson(dto);

        return dto;
    }

    public PlayerDto createUser(PlayerCreateRequest request) {
        var player = playerService.create(request.name);
        return playerMapper.fromPlayer(player);
    }

    public List<PlayerDto> getUsers() {
        var players = playerService.getAll();
        return players.stream().map(player -> playerMapper.fromPlayer(player)).toList();
    }

    public GameDto sow(SowRequest request) {
        var game = gameService.getById(request.gameId);
        var board = game.board;
        var pit = pitService.getFromBoardById(board,request.pitId);
        var player = playerService.getFromGameById(game, request.playerId);
        var playerStore = pitService.getStoreByPlayer(board, player);

        var lastPit = pitService.sowPitAndGetLastPlayedPit(board, pit, request.playerId);
        pitService.checkOppositeAndCapture(lastPit, playerStore, player);

        var gameFinished = gameService.gameFinished(game);
        if (gameFinished) {
            var capturingPlayer = gameService.decideCapturingPlayer(game);
            board = pitService.captureOpponentSeeds(board, capturingPlayer);
            var gameStatus = gameService.getGameStatus(game);
            if (gameStatus == GameStatus.PLAYER_WON) {
                var winningPlayer = gameService.decideWinner(game);
            }
        }

        //get next player
        var nextPlayer = gameService.getNextPlayer(game, lastPit, player);
        game.currentPlayer = nextPlayer;
        gameService.save(game);
        var gameDto = gameMapper.mapFromGame(game);
        return gameDto;
    }
}
