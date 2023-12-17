package com.es.kalaha.infrastructure.repository;

import com.es.kalaha.domain.entities.Pit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PitRepository extends JpaRepository<Pit, UUID> {
}