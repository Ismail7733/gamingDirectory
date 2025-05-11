package com.ismailo.gamingDirectory.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GamerGameId implements Serializable {

    private Long gameId;

    private Long gamerId;

    public GamerGameId(Long gameId, Long gamerId) {
        this.gameId = gameId;
        this.gamerId = gamerId;
    }

    public GamerGameId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamerGameId that = (GamerGameId) o;
        return Objects.equals(gameId, that.gameId) && Objects.equals(gamerId, that.gamerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, gamerId);
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getGamerId() {
        return gamerId;
    }

    public void setGamerId(Long gamerId) {
        this.gamerId = gamerId;
    }
}
