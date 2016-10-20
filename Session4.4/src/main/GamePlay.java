package main;

import controllers.BombController;
import controllers.ControllerController;
import controllers.Enemy.BossController;
import controllers.PlaneController;
import models.Plane;
import utilities.Utils;

import java.awt.*;
import java.util.Random;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class GamePlay {
    public static final int BACKGROUND_WIDTH = 1200, BACKGROUND_HEIGHT = 700;
    public static final int delay = 18;
    public static final int animationDelay = 100;
    public static final long bombLife = 12000;

    private Image background;
    BombController bc;
    private long lastBomb = 0, winTime = 0, loseTime = 0;


    public GamePlay() {
        background = Utils.getImage("resources/background.png");
    }

    public void draw(Graphics g) {
        g.drawImage(background,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,null);
        ControllerController.instance.draw(g);
        if (bc != null) bc.draw(g);
    }

    public void run() {
        long now = System.currentTimeMillis();
        if (now - lastBomb >= bombLife) {
            Random rd = new Random();
            int X = rd.nextInt(800) + 300, Y = rd.nextInt(300) + 300;
            bc = new BombController(X, Y);
            lastBomb = now;
        }

        ControllerController.instance.run();

        if (PlaneController.instance1.getLive()==0 && PlaneController.instance1.getDead() &&
                PlaneController.instance2.getLive()==0 && PlaneController.instance2.getDead())
                     if (loseTime==0) loseTime = System.currentTimeMillis()+2500;

        if (loseTime!=0 && System.currentTimeMillis() >= loseTime) {GameWindow.state = 2; loseTime = 0;}

        if (GameLevel.level[GameLevel.MAX_LEVEL] && winTime==0) winTime = System.currentTimeMillis() + 3000;
        if (winTime!=0 && System.currentTimeMillis() >= winTime) {GameWindow.state = 3; winTime = 0;}
    }
}
