package com.ismailo.gamingDirectory.controllers;

import com.ismailo.gamingDirectory.entities.GamerEntity;
import com.ismailo.gamingDirectory.services.GamerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Gamer", description = "Endpoints for managing gamers")
public class GamerController {

    private GamerService gamerService;

    public GamerController(GamerService gamerService) {
        this.gamerService = gamerService;
    }

    @PostMapping(path = "/gamers")
    @Operation(summary = "Create a new gamer", description = "Adds a new gamer to the system")
    public ResponseEntity<GamerEntity> createGamer(@Valid @RequestBody GamerEntity gamerEntity) {
        return gamerService.createGamer(gamerEntity);
    }

    @GetMapping(path = "/gamers/{id}")
    @Operation(summary = "Find a gamer by ID", description = "Retrieves a gamer by their unique ID")
    public ResponseEntity<GamerEntity> findGamer(@PathVariable("id") long id) {
        return gamerService.findGamer(id);
    }
}
