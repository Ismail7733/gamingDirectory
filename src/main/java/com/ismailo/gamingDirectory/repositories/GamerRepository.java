package com.ismailo.gamingDirectory.repositories;

import com.ismailo.gamingDirectory.entities.GamerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepository extends JpaRepository<GamerEntity, Long> {

}
