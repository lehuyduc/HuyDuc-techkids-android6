package controllers.Enemy;

import controllers.Colliable;
import controllers.CollisionManager;
import controllers.Enemy.Movement.*;
import controllers.PlaneController;
import controllers.SingleController;
import models.GameObject;
import models.Plane;
import views.ImageView;

import java.awt.*;
import java.util.Random;

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
        super(new EnemyBullet(x,y), new ImageView("resources/enemy_bullet.png"));
        checkDefault();
        CollisionManager.instance.add(this);
    }

    public EnemyBulletController(int x,int y,int sx,int sy) {
        super(new EnemyBullet(x,y), new ImageView("resources/enemy_bullet.png"));
        checkDefault();
        CollisionManager.instance.add(this);
    }

    public EnemyBulletController(int x,int y,MovePatternType tp) {
        super(new EnemyBullet(x,y), new ImageView("resources/enemy_bullet.png"));
        if (tp==MovePatternType.ZIGZAG) movePattern = new MovePatternZigzag();
        if (tp==MovePatternType.RANDOM) movePattern = new MovePatternRandom();
        if (tp==MovePatternType.AIM) {
            gameObject.setMoveSpeed(gameObject.getMoveSpeed()*2);
            GameObject plane;
            Random rd = new Random();
            int target = rd.nextInt(2) + 1;
            if (target==1) plane = PlaneController.instance1.getGameObject();
            else plane = PlaneController.instance2.getGameObject();
            if (plane.getDead()) if (target==1) target = 2; else target = 1;
            movePattern = new MovePatternAim(plane.getX()-x,plane.getY()-y);
        }
        checkDefault();
        CollisionManager.instance.add(this);
    }

    public EnemyBulletController(int x,int y,int target) {
        super(new EnemyBullet(x,y), new ImageView("resources/enemy_bullet.png"));
        GameObject plane;
        if (target==1) plane = PlaneController.instance1.getGameObject();
        else plane = PlaneController.instance2.getGameObject();
        movePattern = new MovePatternAim(plane.getX(),plane.getY());
        checkDefault();
        CollisionManager.instance.add(this);
    }


    //**********  COLLISION ******************************************************************

    @Override
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
