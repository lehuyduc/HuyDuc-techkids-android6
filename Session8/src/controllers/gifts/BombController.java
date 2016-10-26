package controllers.gifts;

import controllers.*;
import controllers.managers.EnemyPlaneControllerManager;
import controllers.managers.NotificationCenter;
import main.GameConfig;
import main.GamePlay;
import main.PlayGameScreen;
import models.Bomb;
import models.GameObject;
import models.GameVector;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class BombController extends SingleController implements Colliable {

    private long appear = 0;

    public BombController(int x,int y) {
        super(new Bomb(x,y), new ImageView("bomb.png"));
        gameObject.setDamage(350);
        appear = System.currentTimeMillis();
    }

    public int getDamageRadius() {
        return ((Bomb)gameObject).getDamageRadius();
    }

    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (getDead()) return;
        if (col instanceof PlaneController) {
            NotificationCenter.instance.onEvent(EventType.BOMB_EXPLODE,this);
            gameObject.takeDamage(1000);
            PlayGameScreen.lastBomb = System.currentTimeMillis();
        }
    }

    public boolean deleteNow() {
        long now = System.currentTimeMillis();
        return (now - appear >= GameConfig.DEFAULT_BOMB_LIFE || getDead());
    }

    public void draw(Graphics g) {
        super.draw(g);
    }

    public void run() {
        super.run();
        gameObject.move(new GameVector(0,1));
    }
}
