package controllers.enemy;

import controllers.Colliable;
import controllers.Movement.*;
import controllers.PlaneController;
import controllers.SingleController;
import models.EnemyBullet;
import models.GameObject;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class EnemyBulletController extends SingleController implements Colliable {

    private static int SIZEX = 13, SIZEY = 30;
    private MovePattern movePattern = null;

    public void checkDefault() {
        if (gameObject.getMoveSpeed()==0) gameObject.setMoveSpeed(3);
        if (gameObject.getDamage()==0) gameObject.setDamage(50);
        if (movePattern==null) movePattern = new MovePatternDown();
    }

    public EnemyBulletController(int x,int y) {
        super(new EnemyBullet(x,y), new ImageView("enemy_bullet.png"));
        checkDefault();
    }

    public EnemyBulletController(int x,int y,int sx,int sy) {
        super(new EnemyBullet(x,y), new ImageView("enemy_bullet.png"));
        checkDefault();
    }

    public EnemyBulletController(int x,int y,MovePatternType tp) {
        super(new EnemyBullet(x,y), new ImageView("enemy_bullet.png"));
        if (tp==MovePatternType.ZIGZAG) movePattern = new MovePatternZigzag();
        if (tp==MovePatternType.RANDOM) movePattern = new MovePatternRandom();
        if (tp==MovePatternType.AIM || tp==MovePatternType.FOLLOW) {
            gameObject.setMoveSpeed(gameObject.getMoveSpeed()*2);
            if (tp==MovePatternType.AIM) movePattern = new MovePatternAim();
            else movePattern = new MovePatternFollow();
        }
        checkDefault();
    }


    //**********  COLLISION ******************************************************************

    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    public void onCollide(Colliable col) {
        if (col instanceof PlaneController) {
            gameObject.takeDamage(100);
        }
    }


    //**********  MVC ******************************************************************

    @Override
    public void draw(Graphics g) {
        if (!gameObject.getDead()) gameView.drawImage(g,gameObject);
    }

    @Override
    public void run() {
        movePattern.move(gameObject);
        gameView.run();
    }
}
