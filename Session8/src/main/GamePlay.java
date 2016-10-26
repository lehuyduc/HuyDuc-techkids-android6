package main;

import controllers.gifts.BombController;
import controllers.ControllerController;
import controllers.PlaneController;
import controllers.gifts.GiftManager;
import controllers.gifts.PlayerBulletGift;
import script.ScriptRunner;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class GamePlay {

    public static final int BACKGROUND_WIDTH = GameConfig.DEFAULT_BACKGROUND_WIDTH,
            BACKGROUND_HEIGHT = GameConfig.DEFAULT_BACKGROUND_HEIGHT;

    private Image background;
    BombController bc;
    private long winTime = 0, loseTime = 0;
    public static long lastBomb = 0;
    private GameLevel gameLevel;


    public GamePlay() {
        background = Utils.getImage("background.png");
    }

    public void init() {
        ControllerController.instance.init();
        ScriptRunner.instance.init();
    }

    public void draw(Graphics g) {
        g.drawImage(background,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,null);
        ControllerController.instance.draw(g);
        if (bc != null) bc.draw(g);
    }
    static boolean kt = false;
    static BombController bomb;
    static PlayerBulletGift p1 = new PlayerBulletGift(200,200),
                            p2 = new PlayerBulletGift(400,200),
                            p3 = new PlayerBulletGift(600,200),
                            p4 = new PlayerBulletGift(800,200);

    public void run() {
        long now = System.currentTimeMillis();

        if (!kt) {
            GiftManager.instance.add(p1);
            GiftManager.instance.add(p2);
            GiftManager.instance.add(p3);
            GiftManager.instance.add(p4);
        }
        kt = true;



        ControllerController.instance.run();

        if (PlaneController.instance1.getLive()==0 && PlaneController.instance1.getDead() &&
                PlaneController.instance2.getLive()==0 && PlaneController.instance2.getDead())
                     if (loseTime==0) loseTime = System.currentTimeMillis()+2500;

        if (loseTime!=0 && System.currentTimeMillis() >= loseTime) {GameWindow.state = 2; loseTime = 0;}
      //  if (GameLevel.level[GameLevel.MAX_LEVEL] && winTime==0) winTime = System.currentTimeMillis() + 3000;
        if (winTime!=0 && System.currentTimeMillis() >= winTime) {GameWindow.state = 3; winTime = 0;}
    }
}
