package com.ismailo.gamingDirectory.controllers;

import com.ismailo.gamingDirectory.domain.GamerGameLink;
import com.ismailo.gamingDirectory.repositories.GamerGameLinkRepository;
import com.ismailo.gamingDirectory.services.GamerGameLinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GamerGameLinkController {

    private GamerGameLinkService gamerGameLinkService;

    public GamerGameLinkController(GamerGameLinkService gamerGameLinkService) {
        this.gamerGameLinkService = gamerGameLinkService;
    }

    @PostMapping(path = "/gamer-game-links")
    public ResponseEntity<?> createGamerGameLink(@RequestBody GamerGameLink gamerGameLink) {
        return gamerGameLinkService.createGamerGameLink(gamerGameLink);
    }
}
