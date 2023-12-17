package com.es.kalaha.infrastructure.repository;
import com.es.kalaha.domain.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoardRepository extends JpaRepository<Board, UUID> {
        }