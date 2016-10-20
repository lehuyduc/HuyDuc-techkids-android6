package models;

import main.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class GameObject {


    //********** IMAGE AND DRAWING  ***************************************************************
    public static final int BACKGROUND_WIDTH = 1200, BACKGROUND_HEIGHT = 700;

    protected int x = 0, y = 0;
    protected int sizeX = 0,sizeY = 0;
    protected int cornerX = 0, cornerY = 0;

    //**********  GAMEPLAY  ***************************************************************
    protected int moveSpeed = 0;
    protected int attackSpeed = 0; //ms
    protected int damage = 0;
    protected int health = 1;
    protected boolean enemy = false;
    protected boolean dead = false;
    public boolean yellow = false;
    

    //**********  SET OPERATOR  ***************************************************************
    public void setX(int v) {x = v; cornerX = x - sizeX/2;}

    public void setY(int v) {y = v; cornerY = y - sizeY/2;}

    public void setSizeX(int v) {sizeX = v; cornerX = x - sizeX/2;}

    public void setSizeY(int v) {sizeY = v; cornerY = y - sizeY/2;}

    public void setMoveSpeed(int v) {moveSpeed = v;}

    public void setAttackSpeed(int v) {attackSpeed = v;}

    public void setDamage(int v) {damage = v;}

    public void setHealth(int v) {
        health = v;
        if (health <= 0) dead = true; 
    }

    public void takeDamage(int v) {setHealth(health-v);}

    public void setDead(boolean v) {dead = v;}

    public void setEnemy(boolean v) {enemy = v;}


    //**********  GET OPERATOR  ***************************************************************
    public int getX() {return x;}

    public int getY() {return y;}

    public int getCornerX() {return cornerX;}

    public int getCornerY() {return cornerY;}

    public int getSizeX() {return sizeX;}

    public int getSizeY() {return sizeY;}
    
    public int getMoveSpeed() {return moveSpeed;}

    public int getAttackSpeed() {return attackSpeed;}

    public int getDamage() {return damage;}
    
    public int getHealth() {return health;}

    public boolean getEnemy() {return enemy;}

    public boolean getDead() {return dead;}


    //**********  MOVEMENT  ******************************************************************
    public void moveToScreen() {
        if (x < 0) setX(0);
        if (x > GameWindow.BACKGROUND_WIDTH) setX(GameWindow.BACKGROUND_WIDTH);
        if (y < 0) setY(0);
        if (y > GameWindow.BACKGROUND_HEIGHT) setY(GameWindow.BACKGROUND_HEIGHT);
    }

    public void move(GameVector u) {
        setX(x+u.x);
        setY(y+u.y);
    }

    public void move(GameVector u, boolean needOnScreen) {
        move(u);
        if (needOnScreen) moveToScreen();
    }

    public void moveTo(int X,int Y) {
        setX(X);
        setY(Y);
        moveToScreen();
    }

    public void moveTo(int X,int Y,boolean needOnScreen) {
        moveTo(X,Y);
        if (needOnScreen) moveToScreen();
    }

    //**********  DESTRUCTOR  ******************************************************************
    public boolean oos() {
        return (cornerX <= -sizeX || cornerX >= BACKGROUND_WIDTH
                || cornerY <= -sizeY || cornerY >= BACKGROUND_HEIGHT);
    }

    public boolean deleteNow() {
        return oos() || dead;
    }


    //**********  COLLISION ******************************************************************
    public Rectangle getRect() {
        return new Rectangle(cornerX,cornerY,sizeX,sizeY);
    }

    public boolean collide(GameObject go) {
        return (this.getRect()).intersects(go.getRect());
    }


    //**********  CONSTRUCTOR  ******************************************************************

    public GameObject(int x,int y,int sx,int sy) {
        setX(x); setY(y);
        setSizeX(sx); setSizeY(sy);
    }
}
