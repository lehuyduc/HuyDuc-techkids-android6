package controllers.gifts;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import controllers.Colliable;
import controllers.PlaneController;
import controllers.SingleController;
import controllers.enemy.EnemyPlaneControllerManager;
import main.GameConfig;
import main.GamePlay;
import models.Bomb;
import models.GameObject;
import models.GameVector;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class PlayerBulletGift extends SingleController implements Colliable {

    private long appear = 0;

    public PlayerBulletGift(int x,int y) {
        super(new Bomb(x,y), new ImageView("power-up.png"));
        gameObject.setMoveSpeed(3);
        appear = System.currentTimeMillis();
    }

    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof PlaneController) {
            PlaneController planeController = (PlaneController)col;
            planeController.powerUp(1);
            gameObject.takeDamage(1000);
        }
    }

    public boolean deleteNow() {
        return gameObject.oos() || getDead();
    }

    public void draw(Graphics g) {
        super.draw(g);
    }

    public void run() {
        gameVector = new GameVector(0,gameObject.getMoveSpeed());
        gameObject.move(gameVector);
    }
}
