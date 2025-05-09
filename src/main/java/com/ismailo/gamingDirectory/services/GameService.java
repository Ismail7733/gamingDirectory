package com.ismailo.gamingDirectory.services;

import com.ismailo.gamingDirectory.domain.GameEntity;
import com.ismailo.gamingDirectory.repositories.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameEntity> findAllGames() {
        return gameRepository.findAll();
    }

    public ResponseEntity<GameEntity> createGame(GameEntity gameEntity) {
        GameEntity result = gameRepository.save(gameEntity);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    public ResponseEntity<GameEntity> findGame(Long id) {
        return gameRepository.findById(id)
                .map(gameEntity -> new ResponseEntity<>(gameEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
