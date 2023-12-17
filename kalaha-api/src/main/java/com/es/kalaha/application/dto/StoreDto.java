package com.es.kalaha.application.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class StoreDto {
    public UUID id;
    public int seeds;
    public UUID ownerId;
    public UUID nextId;
}
