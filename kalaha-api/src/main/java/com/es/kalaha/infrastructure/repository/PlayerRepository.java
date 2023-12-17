package com.es.kalaha.infrastructure.repository;

import com.es.kalaha.domain.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
}