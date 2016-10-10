package models;

import control.GameWindow;
import control.Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class EnemyPlane {
    public final static int MAX_PLANE = 2;
    public static int counter = 0;


    //********** IMAGE AND DRAWING  ***************************************************************
    private int BACKGROUND_WIDTH = 1200, BACKGROUND_HEIGHT = 700;
    private int x = 300, y = 200, lastX = 0, lastY = 0;
    private int sizeX = 50, sizeY = 50;
    private int cornerX = x - sizeX/2, cornerY = y - sizeY/2;

    //********** DRAWING IMAGE  ***************************************************************


    //**********  GAMEPLAY  ***************************************************************
    private int moveSpeed = 1;
    private int horizon;
    private int attackSpeed = 2000; //ms
    private int bulletSpeed = 5;
    private int health = 100;
    private boolean dead = false;
    private boolean enemy = true;
    public long lastDead = 0;
    public boolean justDead = false;

    //**********  SET OPERATOR  ***************************************************************
    public void setX(int v) {x = v; cornerX = x - sizeX/2;}

    public void setY(int v) {y = v; cornerY = y - sizeY/2;}

    public void setSizeX(int v) {sizeX = v;}

    public void setSizeY(int v) {sizeY = v;}

    public void setMoveSpeed(int v) {moveSpeed = v;}

    public void setHealth(int v) {health = v; if (health <= 0) {
        dead = true; justDead = true;lastDead = System.currentTimeMillis();}
    }

    public void takeDamage(int v) {setHealth(health-v);}

    public void setDead(boolean v) {dead = v;}


    //**********  GET OPERATOR  ***************************************************************
    public int getX() {return x;}

    public int getY() {return y;}

    public int getCornerX() {return cornerX;}

    public int getCornerY() {return cornerY;}

    public int getSizeX() {return sizeX;}

    public int getSizeY() {return sizeY;}

    public int getMoveSpeed() {return moveSpeed;}

    public int getHealth() {return health;}

    public boolean getDead() {return dead;}

    public int getAttackSpeed() {return attackSpeed;}

    public int getBulletSpeed() {return bulletSpeed;}


    //**********  MOVEMENT OF PLANES  ***************************************************************
    // 1 is down, 0 is up
    public void moveVertical(int down) {
        if (down==1) y += moveSpeed; else y -= moveSpeed;
    }

    // 0 is straight, 1 is right, 2 is left
    public void moveHorizontal(int side) {
        if (side==1) x += moveSpeed; else if (side==2) x -= moveSpeed;
    }

    long lastRan = System.currentTimeMillis();
    public void move() {
        moveVertical(1);

        Random rd = new Random();
        long now = System.currentTimeMillis();
        if (now-lastRan >= 250*GameWindow.delay) {
            horizon = rd.nextInt(3);
            lastRan = now;
        }
        if (cornerX+sizeX >= BACKGROUND_WIDTH && horizon==1) horizon = 2;
        if (cornerX <= 0 && horizon==2) horizon = 1;
        moveHorizontal(horizon);
        setX(x); setY(y);
    }

    public void move(int down, int horizon) {
        moveVertical(down);
        moveHorizontal(horizon);
        setX(x); setY(y);
    }

    //DESTUCTOR
    public boolean oos() {
        return (cornerX+sizeX<0 || cornerX>BACKGROUND_WIDTH || cornerY+sizeY<0 || cornerY>BACKGROUND_HEIGHT);
    }

    //CONSTRUCTOR
    public EnemyPlane(int x,int y) {
        setX(x);
        setY(y);
    }
}
