package com.ismailo.gamingDirectory.services;

import com.ismailo.gamingDirectory.entities.GamerEntity;
import com.ismailo.gamingDirectory.repositories.GamerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GamerService {
    private GamerRepository gamerRepository;

    public GamerService(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    public List<GamerEntity> findAllGamers() {
        return gamerRepository.findAll();
    }

    public ResponseEntity<GamerEntity> createGamer(GamerEntity gamerEntity) {
        GamerEntity result = gamerRepository.save(gamerEntity);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    public ResponseEntity<GamerEntity> findGamer(Long id) {
        return gamerRepository.findById(id)
                .map(gamerEntity -> new ResponseEntity<>(gamerEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
