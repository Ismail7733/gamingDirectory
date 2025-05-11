package com.ismailo.gamingDirectory.controllers;

import com.ismailo.gamingDirectory.entities.GamerEntity;
import com.ismailo.gamingDirectory.entities.GamerGameLinkEntity;
import com.ismailo.gamingDirectory.domain.Level;
import com.ismailo.gamingDirectory.services.GamerGameLinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Gamer-Game Links", description = "Operations for linking gamers to games")
public class GamerGameLinkController {

    private GamerGameLinkService gamerGameLinkService;

    public GamerGameLinkController(GamerGameLinkService gamerGameLinkService) {
        this.gamerGameLinkService = gamerGameLinkService;
    }

    @PostMapping(path = "/gamer-game-links")
    @Operation(summary = "Create a gamer-game link", description = "Creates a link between a gamer and a game with a specified skill level")
    public ResponseEntity<?> createGamerGameLink(@RequestBody GamerGameLinkEntity gamerGameLink) {
        return gamerGameLinkService.createGamerGameLink(gamerGameLink);
    }

    @GetMapping(path = "/gamer-game-links/match")
    @Operation(summary = "Find matching gamers", description = "Returns all gamers from a country who play a specific game at a specific skill level")
    public ResponseEntity<List<GamerEntity>> getMatchingGamers(
            @RequestParam String country,
            @RequestParam Long gameId,
            @RequestParam Level level
    ) {
        return gamerGameLinkService.findMatchingGamers(country, gameId, level);
    }

}
