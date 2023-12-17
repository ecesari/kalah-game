package com.es.kalaha.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateGameWithSettingsRequest {
    public int seedCount;
    public List<PlayerDto> players;
}
