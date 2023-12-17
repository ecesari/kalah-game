package com.es.kalaha.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    public String name;
}

