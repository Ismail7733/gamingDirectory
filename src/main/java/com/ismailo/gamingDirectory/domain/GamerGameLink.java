package com.ismailo.gamingDirectory.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "gamer_game_links")
public class GamerGameLink {

    @EmbeddedId
    private GamerGameId gamerGameId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private Level level;

    public GamerGameLink() {
    }

    public GamerGameLink(GamerGameId gamerGameId, Level level) {
        this.gamerGameId = gamerGameId;
        this.level = level;
    }

    public GamerGameId getGamerGameId() {
        return gamerGameId;
    }

    public void setGamerGameId(GamerGameId gamerGameId) {
        this.gamerGameId = gamerGameId;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
