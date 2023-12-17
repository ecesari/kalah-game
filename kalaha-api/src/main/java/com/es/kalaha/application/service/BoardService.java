package com.es.kalaha.application.service;

import com.es.kalaha.domain.entities.*;
import com.es.kalaha.infrastructure.repository.BoardRepository;
import com.es.kalaha.infrastructure.repository.PitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class BoardService {

    @Autowired
    BoardRepository repository;
    @Autowired
    PitRepository pitRepository;

    public Board create(LinkedList<House> player1Houses, Store player1Store, LinkedList<House> player2Houses, Store player2Store) {

        Board board = new Board();
        board.houses = new ArrayList<>(player1Houses);
        board.houses.addAll(player2Houses);
        board.stores = List.of(player1Store, player2Store);
        repository.save(board);

        return board;
    }
}