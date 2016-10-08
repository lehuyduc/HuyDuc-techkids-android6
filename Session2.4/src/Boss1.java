import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Le Huy Duc on 07/10/2016.
 */
public class Boss1 extends Boss {

    private boolean stopDown = false;
    private boolean has_enter = false;
    private long[] lastAttack = new long[20];
    private long now = System.currentTimeMillis();
    private long actionDuration = 3000;

    //********** IMAGE AND DRAWING  ***************************************************************
    private void initImage() {
        //loading image
        setPhoto("resources/PCarrierH.png");
        sizeX = 132; sizeY = 208;

        //input corners
        File fin = new File("resources/CarrierCorners.txt");
        Scanner fi = null;
        try {
            fi = new Scanner(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        nPoints = fi.nextInt();
        for (int i=0;i<nPoints;i++){
            int j,k;
            j = fi.nextInt(); k = fi.nextInt();
            points2[i] = new Point(j,k);
            points[i] = new Point(points2[i]);
        }
    }


    //********** IMAGE AND DRAWING  ***************************************************************
    public void initVar() {
        isBoss = true;
        dead = true;
        attackSpeed = 200;
        bulletSpeed = 5;
        health = 10000;
        moveSpeed = 2;
    }


    //********** GENERAL MOVEMENT  ***************************************************************
    long lastRan = System.currentTimeMillis();


    public void move() {
        moveSpeed = 0;
        if (!stopDown) moveVertical(1);

        Random rd = new Random();
        long now = System.currentTimeMillis();
        if (now-lastRan >= 10*GameWindow.delay) {
            horizon = rd.nextInt(3);
            lastRan = now;
        }
        moveHorizontal(horizon);
        setX(x);
        setY(y);
    }

    public void enter() {
        setX(BACKGROUND_WIDTH/2);
        setY(0);
        dead = false;
    }


    //**********  ATTACK  ***************************************************************
    // Shoot out a stream of bullets

    public void shootBullet(int i,int x,int y,String type) {
        long now = System.currentTimeMillis();
        if (now-lastAttack[i] < attackSpeed) return;
        lastAttack[i] = now;

        Bullet bullet = new Bullet(true);
        bullet.setX(x);
        bullet.setY(y);
        bullet.setMoveSpeed(bulletSpeed);

        if (type=="laser") {
            bullet.setPhoto("resources/laser_beam3.png");
            bullet.setSizeX(100);
            bullet.setSizeY(300);
            bullet.setMoveSpeed(5);
            bullet.setHealth(100000);
        }

        int n = ++Object.countBullet;
        Object.bullets[n-1] = bullet;
    }


    public void attack1() {
        int tmp = attackSpeed;
        attackSpeed = 700;
        for (int i=0;i<=12;i++) shootBullet(i,points[i].x,points[i].y,"");
        attackSpeed = tmp;
    }

    public void attack2() {
        int tmp = attackSpeed;
        attackSpeed = 2000;
        shootBullet(0,points[0].x,points[0].y,"laser");
        attackSpeed = tmp;
    }

    public void attack3() {
        for (int i=0;i<6;i++) {
            Bot b = new Bot();
            b.setHealth(500);
            b.setPhoto("resources/phoenix.png");
            b.setBulletPhoto("resources/phoenix_bullet2.png");

            Random rd = new Random();
            b.setX(rd.nextInt(9)*100 + 200);
            b.setY(100);
            b.setSizeX(80);
            b.setSizeY(80);

            int n = ++Object.countBot;
            Object.bots[n-1] = b;
        }
    }

    public void attack4() {

    }



    private long lastAction = 0;
    private int type = 0, least = 0;
    private boolean needPause = false;
    public void action() {
        if (!has_enter && y<150) {move(1,0); return;} else has_enter = true;

        now = System.currentTimeMillis();
        if (now < lastAction) return;
        if (type==1) attack1();
        if (type==2) attack2();
        if (type==3 && Object.countBot==1) attack3();
        //if (type==4) attack4();

        now = System.currentTimeMillis();
        if (now-lastAction <= actionDuration) return;
        while (true) {
            Random rd = new Random();
            type = rd.nextInt(3) + 1;
            if (type==3 && Object.countBot >1) continue;
            break;
        }
        lastAction = now+3000;
    }


    public Boss1() {
        initVar();
        initImage();
    }
}
