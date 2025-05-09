package com.ismailo.gamingDirectory.controllers;

import com.ismailo.gamingDirectory.entities.GamerEntity;
import com.ismailo.gamingDirectory.services.GamerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class GamerController {

    private GamerService gamerService;

    public GamerController(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @PostMapping(path = "/gamers")
    public ResponseEntity<GamerEntity> createGamer(@RequestBody GamerEntity gamerEntity) {
        return gamerService.createGamer(gamerEntity);
    }

    @GetMapping(path = "/gamers/{id}")
    public ResponseEntity<GamerEntity> findGamer(@PathVariable("id") long id) {
        return gamerService.findGamer(id);
    }
}
