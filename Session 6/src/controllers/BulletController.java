package controllers;

import controllers.enemy.EnemyPlaneController;
import models.Bullet;
import models.GameObject;
import views.GameView;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class BulletController extends SingleController implements Colliable {

    public void checkDefault(){
        if (gameObject.getDamage()==0) gameObject.setDamage(50);
    }

    public BulletController(GameObject go, GameView gv) {
        super(go, gv);
        checkDefault();
    }

    public BulletController(int x,int y) {
        super(new Bullet(x,y), new ImageView("bullet.png"));
        checkDefault();
    }

    public BulletController(int x,int y,int damage) {
        super(new Bullet(x,y,damage), new ImageView("bullet.png"));
        checkDefault();
    }


    //**********  COLLISION ******************************************************************
    public GameObject getCollisionObject() {
        return gameObject;
    }

    public void onCollide(Colliable col) {
        if (col instanceof EnemyPlaneController) {
            gameObject.takeDamage(100);
        }
    }


    //**********  MVC ******************************************************************
    public synchronized void draw(Graphics g) {
        if (!gameObject.getDead()) gameView.drawImage(g,gameObject);
    }

    public synchronized void run() {
        gameVector.x = 0;
        if (gameObject.getEnemy()) gameVector.y = gameObject.getMoveSpeed();
        else gameVector.y = -gameObject.getMoveSpeed();

        if (!gameObject.getDead()) gameObject.move(gameVector);
    }
}
