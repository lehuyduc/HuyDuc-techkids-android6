package controllers;

import models.Bullet;
import utilities.Utils;
import views.BulletView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class BulletController {
    private Bullet bullet;
    private BulletView bulletView;

    public void drawImage(Graphics g) {
        bulletView.drawImage(g,bullet);
    }

    public BulletController(int x,int y,boolean isEnemy) {
        bullet = new Bullet(x,y,isEnemy);
        if (!isEnemy) bulletView = new BulletView(Utils.getImage("resources/bullet.png"));
        else bulletView = new BulletView(Utils.getImage("resources/bullet_flipped.png"));
    }

    public BulletController(int x,int y,boolean isEnemy, int bulletSpeed) {

    }

    public void run() {
        bullet.move();
    }
}
