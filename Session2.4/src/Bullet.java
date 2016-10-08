import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;


/**
 * Created by Le Huy Duc on 04/10/2016.
 */
public class Bullet {
    //**********
    //**********  OBJECTS QUANTITY MANAGEMENT  ***************************************************************
    public final static int MAX_BULLET = 100;
    public static int counter = 0;


    //**********  IMAGE AND DRAWING  ***************************************************************
    public final int BACKGROUND_WIDTH = Object.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = Object.BACKGROUND_HEIGHT;
    private int x = 0, y = 0; // center
    private int sizeX = 12, sizeY = 30;
    private int cornerX = x - sizeX/2, cornerY = y - sizeY/2;
    private Image photo = null;
    private boolean dead = false;


    //**********  GAMEPLAY  ***************************************************************
    private int moveSpeed = 6;
    private int horizon = 0; // 0 is straight, 1 is left, 2 is down
    private boolean enemy = false;
    private int damage = 100;
    private int health = 1;



    //**********  COPY METHOR  ***************************************************************
    protected void copy(Bullet other) {
        x = other.x; y = other.y;
        sizeX = other.sizeX; sizeY = other.sizeY;
        cornerX = other.cornerX; cornerY = other.cornerY;
        photo = other.photo;
        dead = other.dead;
        moveSpeed = other.moveSpeed;
        enemy = other.enemy;
        horizon = other.horizon;
        damage = other.damage;
        health = other.health;
    }


    //********** DRAWING IMAGE  ***************************************************************
    public void drawImage(Graphics g) {
        g.drawImage(photo,cornerX,cornerY,sizeX,sizeY,null);
    }


    //**********  SET OPERATOR  ***************************************************************
    public void setX(int v) {x = v; cornerX = x - sizeX/2;}

    public void setY(int v) {y = v; cornerY = y - sizeY/2;}

    public void setSizeX(int v) {sizeX = v; setX(x);}

    public void setSizeY(int v) {sizeY = v; setY(y);}

    public void setPhoto(String link) {
        try {
            photo = ImageIO.read(new File(link));
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public void setEnemy(boolean v) {enemy = v; setPhoto("resources/bullet_flipped.png");}

    public void setMoveSpeed(int v) {moveSpeed = v;}

    public void setDead(boolean v) {dead = v;}

    public void setDamage(int v) {damage = v;}

    public void setHealth(int v) {health = v; if (health<=0) dead = true;}

    public void takeDamage(int v) {setHealth(health-v);}


    //**********  GET OPERATOR  ***************************************************************
    public int getX() {return x;}

    public int getY() {return y;}

    public int getSizeX() {return sizeX;}

    public int getSizeY() {return sizeY;}

    public Image getPhoto() {return photo;}

    public int getMoveSpeed() {return moveSpeed;}

    public boolean getEnemy() {return enemy;}

    public int getCornerX() {return cornerX;}

    public int getCornerY() {return cornerY;}

    public boolean getDead() {if (oos()) dead = true; return dead;}

    public int getDamage() {return damage;}

    public int getHealth() {return health;}


    //**********  MOVEMENT  ***************************************************************
    //1 is down, 0 is up
    private void moveVertical(boolean down) {
        if (down) y += moveSpeed; else y -= moveSpeed;
    }

    //0 is straight, 1 is right, 2 is left
    private void moveHorizontal(int side) {
        if (side==1) x += moveSpeed; else if (side==2) x -= moveSpeed;
    }

    public void move() {
        moveVertical(enemy);
        moveHorizontal(0);
        setX(x); setY(y);
    }

    public void move(int horizon) {
        moveVertical(enemy);
        moveHorizontal(horizon);
        setX(x); setY(y);
    }


    //**********  DESTRUCTOR  ***************************************************************
    public boolean oos() {
        return (x < 0 || x>BACKGROUND_WIDTH || y<0 || y>BACKGROUND_HEIGHT);
    }

    public boolean destruct() {
        if (oos() || dead) return true;
        return false;
    }


    //**********  MAIN FUNCTION  ***************************************************************
    public Bullet(boolean isEnemy) {
        counter++;
        setEnemy(isEnemy);
        if (isEnemy) setPhoto("resources/bullet_flipped.png");
        else setPhoto("resources/bullet.png");
    }
}

// chuot phai -> generate -> getter setter