package com.es.kalaha.infrastructure.repository;

import com.es.kalaha.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
}
