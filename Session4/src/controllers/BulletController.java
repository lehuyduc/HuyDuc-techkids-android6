package controllers;

import controllers.Enemy.EnemyPlaneController;
import models.Bullet;
import models.GameObject;
import views.GameView;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class BulletController extends SingleController implements Colliable {

    public BulletController(GameObject go, GameView gv) {
        super(go, gv);
        CollisionManager.instance.add(this);
    }

    public BulletController(int x,int y,boolean isEnemy) {
        super(new Bullet(x,y,isEnemy), new ImageView("resources/bullet.png"));
        if (isEnemy) gameView.setImage("resources/bullet_flipped.png");
        CollisionManager.instance.add(this);
    }


    //**********  COLLISION ******************************************************************
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public boolean getCanCollide() {
        return this.canCollide;
    }

    @Override
    public void setCanCollide(boolean v) {
        this.canCollide = v;
    }

    public void onCollide(Colliable col) {
        if (col instanceof PlaneController) {
            if (gameObject.getEnemy()) gameObject.takeDamage(100);
        }

        if (col instanceof EnemyPlaneController) {
            if (!gameObject.getEnemy()) gameObject.takeDamage(100);
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
