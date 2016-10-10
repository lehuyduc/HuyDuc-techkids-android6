package models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class Plane {
    public final static int MAX_PLANE = 2;
    public static int counter = 0;


    //********** IMAGE AND DRAWING  ***************************************************************
    private int BACKGROUND_WIDTH = 1200, BACKGROUND_HEIGHT = 700;
    private int x = 300, y = 200, lastX = 0, lastY = 0;
    private int sizeX = 50, sizeY = 50;
    private int cornerX = x - sizeX/2, cornerY = y - sizeY/2;

    //********** DRAWING IMAGE  ***************************************************************


    //**********  GAMEPLAY  ***************************************************************
    private int moveSpeed = 10;
    private int attackSpeed = 250; //ms
    private int bulletSpeed = 10;
    private int health = 1;
    private int live = 5;
    private boolean dead = false;
    public long lastDead = 0;
    public boolean justDead = false;

    //**********  SET OPERATOR  ***************************************************************
    public void setX(int v) {x = v; cornerX = x - sizeX/2;}

    public void setY(int v) {y = v; cornerY = y - sizeY/2;}

    public void setSizeX(int v) {sizeX = v;}

    public void setSizeY(int v) {sizeY = v;}

    public void setMoveSpeed(int v) {moveSpeed = v;}

    public void setLive(int v) {live = v;}

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

    public int getLive() {return live;}

    public int getHealth() {return health;}

    public boolean getDead() {return dead;}

    public int getAttackSpeed() {return attackSpeed;}

    public int getBulletSpeed() {return bulletSpeed;}


    //**********  MOVEMENT OF PLANES  ***************************************************************
    public void movePlaneKey(Integer e) {
        switch(e) {
            case KeyEvent.VK_RIGHT:
                x += moveSpeed;
                if (x >= BACKGROUND_WIDTH-sizeX/2) x = BACKGROUND_WIDTH-sizeX/2;
                break;

            case KeyEvent.VK_LEFT:
                x -= moveSpeed;
                if (x < sizeX/2) x = sizeX/2;
                break;

            case KeyEvent.VK_DOWN:
                y += moveSpeed;
                if (y >= BACKGROUND_HEIGHT-sizeY/2) y = BACKGROUND_HEIGHT-sizeY/2;
                break;

            case KeyEvent.VK_UP:
                y -= moveSpeed;
                if (y < sizeY/2) y = sizeY/2;
                break;
        }
        setX(x);
        setY(y);
    }

    public void movePlaneMouse(MouseEvent e) {
        Point p = e.getLocationOnScreen();
        setX(p.x); setY(p.y);
    }

    public Plane(int x,int y) {
        setX(x);
        setY(y);
    }
}
