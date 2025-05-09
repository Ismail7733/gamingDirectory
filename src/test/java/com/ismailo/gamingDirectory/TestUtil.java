package com.ismailo.gamingDirectory;

import com.ismailo.gamingDirectory.domain.GameEntity;
import com.ismailo.gamingDirectory.domain.GamerEntity;

public class TestUtil {
    public static GamerEntity getGamerEntityA() {
        return new GamerEntity("Denmark");
    }
    public static GamerEntity getGamerEntityB() {
        return new GamerEntity("Denmark");
    }
    public static GamerEntity getGamerEntityC() {
        return new GamerEntity("Sweden");
    }

    public static GameEntity getGameEntityA() {
        return new GameEntity("Overwatch");
    }
    public static GameEntity getGameEntityB() {
        return new GameEntity("League");
    }
    public static GameEntity getGameEntityC() {
        return new GameEntity("Minecraft");
    }
}
