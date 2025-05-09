package com.ismailo.gamingDirectory.controllers;

import com.ismailo.gamingDirectory.domain.GamerEntity;
import com.ismailo.gamingDirectory.domain.GamerGameLinkEntity;
import com.ismailo.gamingDirectory.domain.Level;
import com.ismailo.gamingDirectory.services.GamerGameLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GamerGameLinkController {

    private GamerGameLinkService gamerGameLinkService;

    public GamerGameLinkController(GamerGameLinkService gamerGameLinkService) {
        this.gamerGameLinkService = gamerGameLinkService;
    }

    @PostMapping(path = "/gamer-game-links")
    public ResponseEntity<?> createGamerGameLink(@RequestBody GamerGameLinkEntity gamerGameLink) {
        return gamerGameLinkService.createGamerGameLink(gamerGameLink);
    }

    @GetMapping(path = "/gamer-game-links/match")
    public ResponseEntity<List<GamerEntity>> getMatchingGamers(
            @RequestParam String country,
            @RequestParam Long gameId,
            @RequestParam Level level
    ) {
        return gamerGameLinkService.findMatchingGamers(country, gameId, level);
    }

}
