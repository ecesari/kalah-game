package com.es.kalaha.presentation.controller;

import com.es.kalaha.application.dto.BoardDto;
import com.es.kalaha.application.dto.CreateGameWithSettingsRequest;
import com.es.kalaha.application.dto.GameDto;
import com.es.kalaha.application.dto.SowRequest;
import com.es.kalaha.application.service.GameFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("games")
public class GameController {

    private static Logger logger = LogManager.getLogger(GameController.class);

    @Autowired
    private GameFacade facade;

    @GetMapping()
    @CrossOrigin("http://localhost:8081")
    public int all() {
        return 1;
    }

    @PostMapping
    @CrossOrigin("http://localhost:8081")
    public ResponseEntity<GameDto> createGame(@RequestBody CreateGameWithSettingsRequest request) {

        logger.info("Invoking createGame() endpoint... ");

        try {
            var response = facade.createGame(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.info("ERROR");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }

    }

    @PostMapping("/sow")
    @CrossOrigin("http://localhost:8081")
    public ResponseEntity<GameDto> sowSeeds(@RequestBody SowRequest request) {

        logger.info("Invoking sowSeeds() endpoint... ");

        try {
            var response = facade.sow(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.info("ERROR");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }
}
