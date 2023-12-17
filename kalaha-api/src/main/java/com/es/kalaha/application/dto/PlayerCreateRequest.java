package com.es.kalaha.application.dto;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PlayerCreateRequest {
    @NotNull(message = "Name must not be blank")
    public String name;
}
