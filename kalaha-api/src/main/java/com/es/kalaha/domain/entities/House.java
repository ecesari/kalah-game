package com.es.kalaha.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Entity
public class House extends Pit {
    @ManyToOne(cascade = CascadeType.ALL)
    public House opposite;

    public House(int seedCount, Player player) {
        this.seeds = seedCount;
        this.owner = player;
    }

    public House() {
    }

    public boolean canBeSowed(UUID playerId) {
        return true;
    }
    @Override
    public Optional<House> getOpposite() {
        return Optional.ofNullable(opposite);
    }
}
