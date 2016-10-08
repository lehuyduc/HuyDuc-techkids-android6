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
    public final static int MAX_BOT = 50;


    //********** IMAGE AND DRAWING  ***************************************************************
    public static final int BACKGROUND_WIDTH = Object.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = Object.BACKGROUND_HEIGHT;
    protected int x = 0, y = 0, cornerX = 0, cornerY = 0;
    protected int sizeX = 50, sizeY = 50;
    protected Image photo = null;
    protected String bulletPhoto = "";
    protected int horizon = 0;
    protected boolean dead = false;
    protected boolean isBoss = false;

    //**********  GAMEPLAY  ***************************************************************
    protected int moveSpeed = 1;
    protected int attackSpeed = 2000;
    private long lastAttack = 0;
    protected int bulletSpeed = 2;
    protected int health = 100;
    protected int damage = 10;


    //**********  VIRTUAL VARIABLES/FUNCTIONS FOR BOSS  ***************************************************************
    protected Point[] points = new Point[20], points2 = new Point[20];
    protected int nPoints;
    public void action() {

    }




    //********** DRAWING FUNCTIONS  ***************************************************************
    public void drawImage(Graphics g) {g.drawImage(photo,cornerX,cornerY,sizeX,sizeY,null);}


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
    public void setX(int v) {
        x = v;
        cornerX = x - sizeX/2;
        for (int i=0;i<nPoints;i++) points[i].x = cornerX + points2[i].x;
    }

    public void setY(int v) {
        y = v;
        cornerY = y - sizeY/2;
        for (int i=0;i<nPoints;i++) points[i].y = cornerY + points2[i].y;
    }

    public void setSizeX(int v) {sizeX = v; setX(x);}

    public void setSizeY(int v) {sizeY = v; setY(y);}

    public void setPhoto(String link) {
        try {
            photo = ImageIO.read(new File(link));
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public void setPhoto(Image image) {
        photo = image;
    }

    public void setMoveSpeed(int v) {moveSpeed = v;}

    public void setDead(boolean v) {dead = v;}

    public void setHealth(int v) {health = v; if (health<=0) dead = true;}

    public void takeDamage(int dam) {setHealth(health-dam);}

    public void setBulletPhoto(String link) {bulletPhoto = link;}


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


    //********** ATTACK OF PLANES  ***************************************************************
    public void attack(int prob) {
        if (dead) return;
        long now = System.currentTimeMillis();
        if (now - lastAttack <= attackSpeed) return;
        lastAttack = now;

        Random rd = new Random();
        int p = rd.nextInt(100) + 1;
        if (p > prob) return;

        Bullet bullet = new Bullet(true);
        bullet.setMoveSpeed(bulletSpeed);
        bullet.setX(x);
        bullet.setY(y + sizeY/2 - 15);
        bullet.setEnemy(true);
        if (bulletPhoto!="") {
            bullet.setPhoto(bulletPhoto);
            bullet.setSizeX(50);
            bullet.setSizeY(50);
        }

        int n = ++Object.countBullet;
        Object.bullets[n-1] = bullet;
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

    public Bot(int x,int y) {
        setX(x); setY(y);
        setPhoto("resources/enemy_plane_white_2.png");
    }

    public Bot(int x,int y,String link) {
        setX(x); setY(y);
        setPhoto(link);
    }
}
