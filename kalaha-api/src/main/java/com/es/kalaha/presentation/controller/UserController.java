package com.es.kalaha.presentation.controller;

import com.es.kalaha.application.dto.PlayerCreateRequest;
import com.es.kalaha.application.dto.PlayerDto;
import com.es.kalaha.application.service.GameFacade;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private GameFacade facade;

    @GetMapping()
    @CrossOrigin("http://localhost:8081")
    public ResponseEntity<List<PlayerDto>> all() {
        logger.info("Invoking all() endpoint... ");

        try {
            var response = facade.getUsers();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.info("ERROR");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @PostMapping
    @CrossOrigin("http://localhost:8081")
    public ResponseEntity<PlayerDto> createUser(@Valid @RequestBody PlayerCreateRequest dto) {

        logger.info("Invoking createUser() endpoint... ");

        try {
            var response = facade.createUser(dto);
            return ResponseEntity.ok().body(response);


        } catch (Exception e) {
            logger.info("ERROR");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }

    }
}
