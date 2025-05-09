package com.ismailo.gamingDirectory.repositories;

import com.ismailo.gamingDirectory.domain.GamerGameLink;
import com.ismailo.gamingDirectory.domain.GamerGameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamerGameLinkRepository extends JpaRepository<GamerGameLink, GamerGameId> {
}
