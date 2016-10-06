import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

/**
 * Created by Le Huy Duc on 04/10/2016.
 */
public class Bot {
    //**********
    //********** NUMBER OF OBJECT
    public final static int MAX_BOT = 15;


    //********** IMAGE AND DRAWING  ***************************************************************
    public static final int BACKGROUND_WIDTH = Object.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = Object.BACKGROUND_HEIGHT;
    private int x = 300, y = 0, cornerX = 0, cornerY = 0;
    private int sizeX = 50, sizeY = 50;
    private Image photo = null;
    private int horizon = 0;
    private boolean dead = false;

    //********** DRAWING FUNCTIONS  ***************************************************************
    public void drawImage(Graphics g) {g.drawImage(photo,cornerX,cornerY,sizeX,sizeY,null);}


    //**********  GAMEPLAY  ***************************************************************
    private int moveSpeed = 1;
    private int attackSpeed = 200;
    private int health = 100;
    private int damage = 10;


    //**********  COPY CONSTRUCTOR  ***************************************************************
    protected void copy(Bot other) {
        x = other.x; y = other.y;
        cornerX = other.cornerX; cornerY = other.cornerY;
        sizeX = other.sizeX; sizeY = other.sizeY;
        photo = other.photo;
        horizon = other.horizon;
        dead = other.dead;
        moveSpeed = other.moveSpeed;
        attackSpeed = other.attackSpeed;
        health = other.health;
        damage = other.damage;
    }

    //**********  SELFMADE FUNCTIONS  ***************************************************************
    public int abs(int x) {if (x>=0) return x; else return -x;}

    public int abs(int x,int y) {return abs(x-y);}

    //**********  SET OPERATOR  ***************************************************************
    public void setX(int v) {x = v; cornerX = x - sizeX/2;}

    public void setY(int v) {y = v; cornerY = y - sizeY/2;}

    public void setSizeX(int v) {sizeX = v;}

    public void setSizeY(int v) {sizeY = v;}

    public void setPhoto(String link) {
        try {
            photo = ImageIO.read(new File(link));
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public void setMoveSpeed(int v) {moveSpeed = v;}

    public void setDead(boolean v) {dead = v;}

    public void setHealth(int v) {health = v;}

    public void takeDamage(int dam) {setHealth(health-dam); if (health<=0) dead = true;}


    //**********  GET OPERATOR  ***************************************************************
    public int getX() {return x;}

    public int getY() {return y;}

    public int getSizeX() {return sizeX;}

    public int getSizeY() {return sizeY;}

    public Image getPhoto() {return photo;}

    public int getMoveSpeed() {return moveSpeed;}

    public int getCornerX() {return cornerX;}

    public int getCornerY() {return cornerY;}

    public boolean getDead() {return dead;}

    public int getHealth() {return health;}



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
        if (now-lastRan >= 10*GameWindow.delay) {
            horizon = rd.nextInt(3);
            lastRan = now;
        }
        moveHorizontal(horizon);
        setX(x);
        setY(y);
    }

    public void entrance() {

    }


    //**********  DESTRUCTOR  ***************************************************************
    public boolean oos() {
        return (x < 0 || x>BACKGROUND_WIDTH || y<0 || y>BACKGROUND_HEIGHT);
    }

    public boolean destruct() {
        if (oos() || dead) return true;
        return false;
    }

    public Bot() {
        setPhoto("resources/enemy_plane_white_2.png");
    }
}
