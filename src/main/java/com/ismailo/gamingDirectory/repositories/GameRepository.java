package com.ismailo.gamingDirectory.repositories;

import com.ismailo.gamingDirectory.domain.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Long> {

}
