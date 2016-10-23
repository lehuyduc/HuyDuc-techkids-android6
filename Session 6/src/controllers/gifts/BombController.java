package controllers.gifts;

import controllers.Colliable;
import controllers.CollisionManager;
import controllers.PlaneController;
import controllers.SingleController;
import controllers.enemy.EnemyPlaneControllerManager;
import main.GameConfig;
import main.GamePlay;
import models.Bomb;
import models.GameObject;
import models.Plane;
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

    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (getDead()) return;
        if (col instanceof PlaneController) {
            EnemyPlaneControllerManager.instance.takeDamage(gameObject.getDamage());
            gameObject.takeDamage(1000);
            GamePlay.lastBomb = System.currentTimeMillis();
        }
    }

    public boolean deleteNow() {
        long now = System.currentTimeMillis();
        return (now - appear >= GameConfig.BOMB_LIFE || getDead());
    }

    public void draw(Graphics g) {
        super.draw(g);
    }

    public void run() {
        super.run();
    }
}
