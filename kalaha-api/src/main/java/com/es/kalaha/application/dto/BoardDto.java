package com.es.kalaha.application.dto;

import jakarta.persistence.Id;

import java.util.List;
import java.util.UUID;

public class BoardDto {
    public List<HouseDto> houses;
    public List<StoreDto> stores;
    @Id
    public UUID id;
}
