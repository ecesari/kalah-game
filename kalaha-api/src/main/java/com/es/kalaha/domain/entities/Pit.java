package com.es.kalaha.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;


@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Pit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public int seeds;
    @ManyToOne
    public Player owner;
    @ManyToOne(cascade = CascadeType.ALL)
    public Pit next;
    public abstract boolean canBeSowed(UUID playerId);
    public Optional<House> getOpposite() {
        return Optional.empty();
    }
}