package com.es.kalaha.application.dto;

import com.es.kalaha.domain.entities.GameStatus;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GameDto {
    public UUID id;
    public List<PlayerDto> players;
    public BoardDto board;
    public GameStatus gameStatus;
    public PlayerDto currentPlayer;
}
