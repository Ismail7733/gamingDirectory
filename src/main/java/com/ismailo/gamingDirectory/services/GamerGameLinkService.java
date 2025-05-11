package com.ismailo.gamingDirectory.services;

import com.ismailo.gamingDirectory.entities.GamerEntity;
import com.ismailo.gamingDirectory.entities.GamerGameLinkEntity;
import com.ismailo.gamingDirectory.domain.Level;
import com.ismailo.gamingDirectory.repositories.GameRepository;
import com.ismailo.gamingDirectory.repositories.GamerGameLinkRepository;
import com.ismailo.gamingDirectory.repositories.GamerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamerGameLinkService {

    private GamerGameLinkRepository gamerGameLinkRepository;
    private GamerRepository gamerRepository;
    private GameRepository gameRepository;

    public GamerGameLinkService(GamerGameLinkRepository gamerGameLinkRepository, GamerRepository gamerRepository, GameRepository gameRepository) {
        this.gamerGameLinkRepository = gamerGameLinkRepository;
        this.gamerRepository = gamerRepository;
        this.gameRepository = gameRepository;
    }

    public ResponseEntity<?> createGamerGameLink(GamerGameLinkEntity gamerGameLink) {
        Long gamerId = gamerGameLink.getGamerGameId().getGamerId();
        Long gameId = gamerGameLink.getGamerGameId().getGameId();

        if (!gamerRepository.existsById(gamerId)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Gamer with ID " + gamerId + " does not exist.");
        }

        if (!gameRepository.existsById(gameId)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Game with ID " + gameId + " does not exist.");
        }

        GamerGameLinkEntity saved = gamerGameLinkRepository.save(gamerGameLink);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    public ResponseEntity<List<GamerEntity>> findMatchingGamers(String country, Long gameId, Level level) {
        List<GamerEntity> gamers = gamerGameLinkRepository.findGamerIdsByCountryGameIdAndLevel(country, gameId, level);
        return new ResponseEntity<>(gamers, HttpStatus.OK);
    }
}
