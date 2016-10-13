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
    int BACKGROUND_WIDTH = GameWindow.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = GameWindow.BACKGROUND_HEIGHT;
    protected int x, y;
    protected int sizeX,sizeY;
    protected int cornerX, cornerY;

    //**********  GAMEPLAY  ***************************************************************
    protected int moveSpeed = 0;
    protected int attackSpeed = 250; //ms
    protected int damage = 0;
    protected int health = 1;
    protected boolean enemy = false;
    protected boolean dead = false;
    

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
    public void move(GameVector u) {
        setX(x+u.x);
        setY(y+u.y);
    }

    public void moveTo(int X,int Y) {
        setX(X);
        setY(Y);
    }

    //**********  DESTRUCTOR  ******************************************************************
    public boolean oos() {
        return (cornerX < -sizeX || cornerX > BACKGROUND_WIDTH || cornerY < -sizeY || cornerY > BACKGROUND_HEIGHT);
    }

    public boolean needDelete() {
        return false;
    }


    //**********  COLLISION ******************************************************************
    public Rectangle getRect() {
        return new Rectangle(cornerX,cornerY,sizeX,sizeY);
    }

    public boolean collide(GameObject b) {
        return (this.getRect()).intersects(b.getRect());
    }


    //**********  CONSTRUCTOR  ******************************************************************
    public GameObject() {

    }

    public GameObject(int x,int y) {
        setX(x);
        setY(y);
    }

    public GameObject(int x,int y,int sx,int sy) {
        setX(x); setY(y);
        setSizeX(x); setSizeY(y);
    }
}
