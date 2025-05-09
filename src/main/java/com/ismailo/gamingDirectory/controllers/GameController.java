package com.ismailo.gamingDirectory.controllers;

import com.ismailo.gamingDirectory.domain.GameEntity;
import com.ismailo.gamingDirectory.domain.GamerEntity;
import com.ismailo.gamingDirectory.services.GameService;
import com.ismailo.gamingDirectory.services.GamerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(path = "/games")
    public ResponseEntity<GameEntity> createGame(@RequestBody GameEntity gameEntity) {
        return gameService.createGame(gameEntity);
    }

    @GetMapping(path = "/games/{id}")
    public ResponseEntity<GameEntity> findGame(@PathVariable("id") long id) {
        return gameService.findGame(id);
    }
}
