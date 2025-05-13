package com.ismailo.gamingDirectory.controllers;

import com.ismailo.gamingDirectory.entities.GameEntity;
import com.ismailo.gamingDirectory.services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Game", description = "Endpoints for managing games")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(path = "/games")
    @Operation(summary = "Create a new game", description = "Adds a new game to the system")
    public ResponseEntity<GameEntity> createGame(@Valid @RequestBody GameEntity gameEntity) {
        System.out.println("Received: " + gameEntity.getName());
        return gameService.createGame(gameEntity);
    }

    @GetMapping(path = "/games/{id}")
    @Operation(summary = "Find a game by ID", description = "Retrieves a game by its unique ID")
    public ResponseEntity<GameEntity> findGame(@PathVariable("id") long id) {
        return gameService.findGame(id);
    }
}
