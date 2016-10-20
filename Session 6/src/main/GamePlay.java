package main;

import controllers.gifts.BombController;
import controllers.ControllerController;
import controllers.PlaneController;
import script.Script;
import script.ScriptRunner;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class GamePlay {
    public static final int BACKGROUND_WIDTH = GameConfig.DEFAULT_BACKGROUND_WIDTH,
                            BACKGROUND_HEIGHT = GameConfig.DEFAULT_BACKGROUND_HEIGHT;
    public static final int threadDelay = GameConfig.DEFAULT_THREAD_DELAY;
    public static final int animationDelay = GameConfig.DEFAULT_ANIMATION_DELAY;
    public static final long bombLife = GameConfig.BOMB_LIFE;
    public static long lastBomb = 0;

    private Image background;
    BombController bc;
    private long winTime = 0, loseTime = 0;

    public GamePlay() {
        background = Utils.getImage("background.png");
    }

    public void init() {
        ControllerController.instance.init();
        ScriptRunner.instance.init();
        GameLevel.init();
    }

    public void draw(Graphics g) {
        g.drawImage(background,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,null);
        ControllerController.instance.draw(g);
        if (bc != null) bc.draw(g);
    }

    public void run() {
        long now = System.currentTimeMillis();

        ScriptRunner.instance.run();
        ControllerController.instance.run();

        if (PlaneController.instance1.getLive()==0 && PlaneController.instance1.getDead() &&
                PlaneController.instance2.getLive()==0 && PlaneController.instance2.getDead())
                     if (loseTime==0) loseTime = System.currentTimeMillis()+2500;

        if (loseTime!=0 && System.currentTimeMillis() >= loseTime) {GameWindow.state = 2; loseTime = 0;}
        if (GameLevel.level[GameLevel.MAX_LEVEL] && winTime==0) winTime = System.currentTimeMillis() + 3000;
        if (winTime!=0 && System.currentTimeMillis() >= winTime) {GameWindow.state = 3; winTime = 0;}
    }
}
