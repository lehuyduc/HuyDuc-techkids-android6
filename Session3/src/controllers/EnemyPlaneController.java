package controllers;

import models.Bullet;
import models.EnemyPlane;
import views.EnemyPlaneView;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class EnemyPlaneController {
    private EnemyPlane enemyPlane;
    private EnemyPlaneView enemyPlaneView;
    private Vector<BulletController> bulletControllers = new Vector<>();

    public void drawImage(Graphics g) {
        enemyPlaneView.drawImage(g,enemyPlane);
        for (BulletController bulletController : bulletControllers) bulletController.drawImage(g);
    }

    public void move() {
        enemyPlane.move();
    }

    long lastAttack = 0;
    public void attack() {
        long now = System.currentTimeMillis();
        if (now - lastAttack < enemyPlane.getAttackSpeed()) return;
        lastAttack = now;
        BulletController bulletController = new BulletController(enemyPlane.getX(),enemyPlane.getY()+enemyPlane.getSizeY()/2,true);
        bulletControllers.add(bulletController);
    }


    public EnemyPlaneController(EnemyPlane enemyPlane, EnemyPlaneView enemyPlaneView) {
        this.enemyPlane = enemyPlane;
        this.enemyPlaneView = enemyPlaneView;
    }

    public boolean destruct() {
        return enemyPlane.oos();
    }

    public void run() {
        enemyPlane.move();
        for (BulletController bulletController : bulletControllers) {

            bulletController.run();
        }
        attack();
    }
}
