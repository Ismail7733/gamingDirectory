package com.ismailo.gamingDirectory.repositories;

import com.ismailo.gamingDirectory.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Long> {

}
