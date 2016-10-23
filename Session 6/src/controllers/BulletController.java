package controllers;

import controllers.Movement.*;
import controllers.attack.BulletType;
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

    private BulletType bulletType;
    private MovePattern movePattern;

    public void checkDefault(){
        if (bulletType==BulletType.PLAYER_BULLET) {
            gameObject.setDamage(50);
            gameView.setImage("bullet.png");
            movePattern = new MovePatternUp();
        }

        if (bulletType==bulletType.PLAYER_BULLET_LEFT) {
            gameObject.setDamage(50);
            gameView.setImage("bullet-left.png");
            movePattern = new MovePatternAim(Math.PI*2/3);
        }

        if (bulletType==bulletType.PLAYER_BULLET_RIGHT) {
            gameObject.setDamage(50);
            gameView.setImage("bullet-right.png");
            movePattern = new MovePatternAim(Math.PI/3);
        }
    }

    private BulletController(GameObject go, GameView gv) {
        super(go, gv);
        checkDefault();
    }

    private BulletController(int x, int y, BulletType type, MovePattern movePattern) {
        super(new Bullet(x,y),new ImageView("bullet.png"));
        this.bulletType = type;
        this.movePattern = movePattern;
        checkDefault();
    }

    public static BulletController create(int x,int y,BulletType bulletType,MovePatternType movePatternType) {
        return new BulletController(x,y,bulletType,MovePattern.create(movePatternType));
    }


    //**********  COLLISION ******************************************************************
    public GameObject getCollisionObject() {
        return gameObject;
    }

    public void onCollide(Colliable col) {
        if (col instanceof EnemyPlaneController) {
            GameObject enemyPlane = col.getCollisionObject();
            enemyPlane.takeDamage(getDamage());
        }
    }


    //**********  MVC ******************************************************************
    public synchronized void draw(Graphics g) {
        if (!gameObject.getDead()) gameView.drawImage(g,gameObject);
    }

    public synchronized void run() {
        movePattern.move(gameObject);

        if (!gameObject.getDead()) gameObject.move(gameVector);
    }
}
