package controllers.enemy;

import controllers.*;
import controllers.Movement.MovePattern;
import models.Boss;
import models.GameObject;
import models.GameVector;
import utilities.Utils;
import views.GameView;
import views.ImageView;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Le Huy Duc on 15/10/2016.
 */
public class BossController extends EnemyPlaneController implements Colliable {

    private SingleControllerManager bulletManager;
    public int nPoints = 0;
    public Point[] points = new Point[20];
    public Point[] points2 = new Point[20];
    private long[] lastShot = new long[20];
    private MovePattern movePattern = null;
    private boolean entrance = false;
    private GameObject root = new GameObject(600,160,0,0);

    public void checkDefault() {
        bulletManager = new SingleControllerManager();
        gameObject.setHealth(8500);
        gameObject.setDead(false);
        canCollide = true;
        gameObject.setX(600);
        gameObject.setY(0);
        deathEffect = false;
        entrance = false;
        isBoss = true;
    }

    public BossController(GameObject go, GameView gv) {
        super(go, gv);
        File fi = new File("resources/CarrierCorners.txt");
        Scanner fin = null;
        try {
            fin = new Scanner(fi);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        nPoints = fin.nextInt();
        for (int i=0;i<nPoints;i++) {
            int j,k;
            j = fin.nextInt();
            k = fin.nextInt();
            points2[i] = new Point(j,k);
            points[i] = new Point(j,k);
        }

        checkDefault();
    }

    public boolean deleteNow() {
        return gameObject.deleteNow() && bulletManager.deleteNow();
    }

    //**********  COLLISION  ******************************************************************
    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public boolean getCanCollide() {
        return canCollide;
    }

    @Override
    public void setCanCollide(boolean v) {
        canCollide = v;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof BulletController) {
            GameObject bullet = col.getCollisionObject();
            gameObject.takeDamage(bullet.getDamage());
        }
    }

    public boolean collide(GameObject go) {
        double r = Utils.distance(new Point(go.getX(),go.getY()), new Point(go.getCornerX(),go.getCornerY()));
        for (int i=0;i<nPoints;i++)
            if (Utils.distance(points[i], new Point(go.getX(),go.getY())) <= r) return true;
        return false;
    }


    //**********  MOVEMENT  ***************************************************************
    Random rd = new Random();
    int side = 0;
    boolean inited = false, goHome = false;
    boolean inMove = false;
    long lastStop = 0;

    //****************************************
    void move1() {
        long now = System.currentTimeMillis();
        if (!inited) {inited = true; side = rd.nextInt(2);}
        if (gameObject.getX() <= 150 || gameObject.getX() >= BACKGROUND_WIDTH-150) {goHome = true;}
        if (!goHome) {
            if (side==0) gameObject.move(new GameVector(-1,0));
            else gameObject.move(new GameVector(1,0));
        }
        else {
            int k = 0;
            if (gameObject.getX() < root.getX()) k = 1;
            else if (gameObject.getX() > root.getX()) k = -1;
            gameObject.move(new GameVector(k,0));
        }
        if (gameObject.getX() == root.getX()) {inMove = false; lastStop = System.currentTimeMillis();}
    }

    void move2() {

    }

    void move3() {

    }

    void move() {
        if (!entrance && gameObject.getY() < root.getY()) {gameObject.move(new GameVector(0,1)); return;}
        else if (gameObject.getY() >= root.getY()) entrance = true;

        long now = System.currentTimeMillis();
        if (!inMove && now-lastStop >= 4000) {
            inMove = true; inited = false;
            goHome = false;
            tp = rd.nextInt(3) + 1;
        }

        if (inMove) {
            if (tp==1) move1();
            if (tp==2) move2();
            if (tp==3) move3();
        }
    }


    //**********  ATTACK  ***************************************************************
    // Shoot out a stream of bullets

    public void shootBullet(int i,int x,int y,String type) {

        EnemyBulletController bullet = new EnemyBulletController(x,y);
        bullet.setMoveSpeed(8);
        bullet.setImage("bullet_flipped.png");
        bullet.setSizeX(13);
        bullet.setSizeY(30);

        if (type=="laser") {
            bullet.setImage("laser_beam3.png");
            bullet.setSizeX(100);
            bullet.setSizeY(325);
            bullet.setMoveSpeed(9);
            bullet.setHealth(100000);
        }

        bulletManager.add(bullet);
    }


    long lastAtk1 = 0;
    public void attack1() {
        long now = System.currentTimeMillis();
        if (now - lastAtk1 < 1600) return;
        lastAtk1 = now;
        for (int i=0;i<=8;i++) shootBullet(i,points[i].x,points[i].y,"");
    }

    long lastAtk2 = 0;
    public void attack2() {
        long now = System.currentTimeMillis();
        if (now - lastAtk2 < 1600) return;
        lastAtk2 = now;
        shootBullet(0,points[0].x,points[0].y,"laser");
    }

    public synchronized void attack3() {
        for (int i=0;i<4;i++) {
            EnemyPlaneController epc = new EnemyPlaneController(rd.nextInt(13)*80+100,10);
            epc.setSizeX(80);
            epc.setSizeY(80);
            epc.setImage("phoenix.png");
            epc.setBulletImage("phoenix_bullet3.png");
            epc.setHealth(250);
            epc.setMoveSpeed(1);
            epc.setAttackSpeed(2600);
            epc.setDamage(125);
            BackManagerSingle.instance.add(epc);
        }
    }

    public void attack4() {

    }

    public void draw(Graphics g) {
        bulletManager.draw(g);
        gameView.drawImage(g,gameObject);
    }

    long lastAttack = 0;
    int tp = 0, mt = 0;

    public synchronized void run() {
        bulletManager.run();
        if (gameObject.getDead()) deathEffect();
        if (gameObject.getDead()) return;

        for (int i=0;i<nPoints;i++) {
            points[i].x = points2[i].x + gameObject.getCornerX();
            points[i].y = points2[i].y + gameObject.getCornerY();
        }

        Random rd = new Random();

        move();
        if (!entrance) return;

        long now = System.currentTimeMillis();
        if (now - lastAttack >= 8000) {
            tp = rd.nextInt(3) + 1;
            if (tp==3 && EnemyPlaneControllerManager.instance.size() >= 3) tp = rd.nextInt(3);
            lastAttack = now;
        }
        if (EnemyPlaneControllerManager.instance.size() >= 5) tp = 1;

        if (now - lastAttack <= 4900) {
            if (tp == 1) attack1();
            if (tp == 2) attack2();
            if (tp == 3) attack3();
        }
    }

    public static final BossController instance =
            new BossController(new Boss(0,0), new ImageView("PCarrierH.png"));
}
