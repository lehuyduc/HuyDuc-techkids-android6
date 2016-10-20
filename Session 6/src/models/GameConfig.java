package models;

import main.GamePlay;

/**
 * Created by Le Huy Duc on 16/10/2016.
 */
public class GameConfig {

    private static final int DEFAULT_WIDTH = GamePlay.BACKGROUND_WIDTH;
    private static final int DEFAULT_HEIGHT = GamePlay.BACKGROUND_HEIGHT;
    private static final int DEFAULT_DELAY = 17;

    private int threadDelay = 17;
    private int BACKGROUND_WIDTH = GamePlay.BACKGROUND_WIDTH;
    private int BACKGROUND_HEIGHT = GamePlay.BACKGROUND_HEIGHT;

    public double getSecond(int count) {
        return (double)(threadDelay*count)/1000;
    }

    public double getMiliSecond(int count) {
        return (double)(threadDelay*count);
    }

    private GameConfig() {
        this.threadDelay = DEFAULT_DELAY;
        this.BACKGROUND_WIDTH = DEFAULT_WIDTH;
        this.BACKGROUND_HEIGHT = DEFAULT_HEIGHT;
    }

    public static final GameConfig instance = new GameConfig();
}
