package main;

/**
 * Created by Le Huy Duc on 16/10/2016.
 */
public class GameConfig {

    public static final int DEFAULT_BACKGROUND_WIDTH = 1200;
    public static final int DEFAULT_BACKGROUND_HEIGHT = 700;
    public static final int DEFAULT_THREAD_DELAY = 18;
    public static final int DEFAULT_ANIMATION_DELAY = 40;
    public static final long DEFAULT_EXPLOSION_DELAY = 100;
    public static final int DEFAULT_BOMB_LIFE = 7000;

    public static final int BACKGROUND_WIDTH = DEFAULT_BACKGROUND_WIDTH,
                            BACKGROUND_HEIGHT = DEFAULT_BACKGROUND_HEIGHT;
    public static final int threadDelay = DEFAULT_THREAD_DELAY;
    public static final int animationDelay = DEFAULT_ANIMATION_DELAY;
    public static long explosionDelay = DEFAULT_EXPLOSION_DELAY;
    public static final long bombLife = DEFAULT_BOMB_LIFE;

//
//    public double getSecond(int count) {
//        return (double)(threadDelay*count)/1000;
//    }
//
//    public double getMiliSecond(int count) {
//        return (double)(threadDelay*count);
//    }

    private GameConfig() {

    }


}
