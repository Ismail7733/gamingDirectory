package com.ismailo.gamingDirectory.repositories;

import com.ismailo.gamingDirectory.entities.GamerEntity;
import com.ismailo.gamingDirectory.entities.GamerGameLinkEntity;
import com.ismailo.gamingDirectory.entities.GamerGameId;
import com.ismailo.gamingDirectory.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamerGameLinkRepository extends JpaRepository<GamerGameLinkEntity, GamerGameId> {

    @Query("""
        SELECT g
        FROM GamerGameLinkEntity ggl
        JOIN GamerEntity g ON g.id = ggl.gamerGameId.gamerId
        WHERE g.country = :country
            AND ggl.gamerGameId.gameId = :gameId
            AND ggl.level = :level
    """)
    List<GamerEntity> findGamerIdsByCountryGameIdAndLevel(
            @Param("country") String country,
            @Param("gameId") Long gameId,
            @Param("level") Level level
    );

}
