package com.es.kalaha.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Store extends Pit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    public Store(Player owner) {
        this.owner = owner;
    }

    public Store() {
    }

    public boolean canBeSowed(UUID playerId) {
        return owner.id.equals(playerId);
    }
}
