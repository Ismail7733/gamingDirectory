package com.ismailo.gamingDirectory.repositories;

import com.ismailo.gamingDirectory.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

}
