package main;

import controllers.ControllerController;
import controllers.PlaneController;
import controllers.gifts.BombController;
import controllers.gifts.GiftManager;
import controllers.gifts.PlayerBulletGift;
import script.ScriptRunner;
import utilities.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Le Huy Duc on 25/10/2016.
 */
public class PlayGameScreen extends GameScreen {

    public static final int BACKGROUND_WIDTH = GameConfig.DEFAULT_BACKGROUND_WIDTH,
            BACKGROUND_HEIGHT = GameConfig.DEFAULT_BACKGROUND_HEIGHT;

    private Image background;
    BombController bc;
    private long winTime = 0, loseTime = 0;
    public static long lastBomb = 0;

    public PlayGameScreen(ScreenManager screenManager) {
        super(screenManager);
        background = Utils.getImage("background.png");
        init();
    }

    void init() {
        ControllerController.instance.init();
        ScriptRunner.instance.init();
        GameLevel.instance.init();
    }

    @Override
    void update(Graphics g) {
        g.drawImage(background,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,null);
        ControllerController.instance.draw(g);
        if (bc != null) bc.draw(g);
    }


    private boolean kt = false;
    private BombController bomb;
    private PlayerBulletGift p1 = new PlayerBulletGift(200,200),
            p2 = new PlayerBulletGift(400,200),
            p3 = new PlayerBulletGift(600,200),
            p4 = new PlayerBulletGift(800,200);


    @Override
    void run() {
        long now = System.currentTimeMillis();

        if (!kt) {
            bc = new BombController(600,400);
            GiftManager.instance.add(p1);
            GiftManager.instance.add(p2);
            GiftManager.instance.add(p3);
            GiftManager.instance.add(p4);
            GiftManager.instance.add(bc);

        }
        kt = true;

        GameLevel.instance.run();

        ControllerController.instance.run();

        if (PlaneController.instance1.getLive()==0 && PlaneController.instance1.getDead() &&
                PlaneController.instance2.getLive()==0 && PlaneController.instance2.getDead())
            if (loseTime==0) loseTime = System.currentTimeMillis()+2500;

        if (loseTime!=0 && System.currentTimeMillis() >= loseTime) {
            this.screenManager.change(new LoseGameScreen(screenManager),false);
        }
//        if (GameLevel.level[GameLevel.MAX_LEVEL] && winTime==0) winTime = System.currentTimeMillis() + 3000;
//        if (winTime!=0 && System.currentTimeMillis() >= winTime) {GameWindow.state = 3; winTime = 0;}
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        PlaneController.instance1.kil.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PlaneController.instance1.kil.keyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        PlaneController.instance2.mil.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PlaneController.instance2.mil.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        PlaneController.instance2.mil.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        PlaneController.instance2.mil.mouseMoved(e);
    }
}
