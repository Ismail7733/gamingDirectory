package com.ismailo.gamingDirectory.repositories;

import com.ismailo.gamingDirectory.entities.GamerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamerRepository extends JpaRepository<GamerEntity, Long> {

}
