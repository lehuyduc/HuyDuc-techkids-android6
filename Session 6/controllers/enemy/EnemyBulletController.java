package controllers.enemy;

import controllers.Colliable;
import controllers.Movement.*;
import controllers.PlaneController;
import controllers.SingleController;
import controllers.attack.BulletType;
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
    private BulletType bulletType;

    private void typeEnemyBullet() {
        setSizeX(27);
        setSizeY(27);
        setDamage(50);
    }

    private void typeBulletFlip() {
        setMoveSpeed(7);
        setSizeX(20);
        setSizeY(27);
        setDamage(50);
        gameView.setImage("bullet_flipped.png");
    }

    private void typePhoenix() {
        setSizeX(35);
        setSizeY(35);
        setDamage(125);
        gameView.setImage("phoenix_bullet3.png");
    }

    private void typeLaser() {
        setSizeX(100);
        setSizeY(325);
        setMoveSpeed(9);
        setHealth(100000);
        setDamage(1000);
        setImage("laser_beam3.png");
    }

    public void checkDefault() {
        setMoveSpeed(3);
        if (bulletType==BulletType.ENEMY_BULLET) typeEnemyBullet();

        if (bulletType==BulletType.BULLET_FLIP) typeBulletFlip();

        if (bulletType==BulletType.PHOENIX) typePhoenix();

        if (bulletType==BulletType.LASER) typeLaser();
    }

    protected EnemyBulletController(int x,int y,BulletType bulletType, MovePatternType bulletPatternType) {
        super(new EnemyBullet(x,y), new ImageView("enemy_bullet.png"));
        this.bulletType = bulletType;
        this.movePattern = MovePattern.create(bulletPatternType);
        checkDefault();
    }

    public static EnemyBulletController create(int x, int y, BulletType bulletType, MovePatternType bulletPatternType) {
        return new EnemyBulletController(x,y,bulletType,bulletPatternType);
    }


    //**********  COLLISION ******************************************************************

    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    public void onCollide(Colliable col) {
        if (col instanceof PlaneController) {
            GameObject plane = col.getCollisionObject();
            plane.takeDamage(getDamage());
        }
    }


    //**********  MVC ******************************************************************

    @Override
    public void draw(Graphics g) {
        if (!gameObject.getDead()) gameView.drawImage(g,gameObject);
    }

    @Override
    public void run() {
        if (movePattern!=null) movePattern.move(gameObject);
        gameView.run();
    }
}
