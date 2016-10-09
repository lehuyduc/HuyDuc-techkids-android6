import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


/**
 * Created by Le Huy Duc on 03/10/2016.
 */

public class MyPlane {
    //**********
    //********** NUMBER OF OBJECT
    public final static int MAX_PLANE = 2;
    public static int counter = 0;


    //********** IMAGE AND DRAWING  ***************************************************************
    private int BACKGROUND_WIDTH = Object.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = Object.BACKGROUND_HEIGHT;
    private int x = 300, y = 200, lastX = 0, lastY = 0;
    private int sizeX = 50, sizeY = 50;
    private int cornerX = x - sizeX/2, cornerY = y - sizeY/2;
    private Image photo = null;

    //********** DRAWING IMAGE  ***************************************************************
    public void drawImage(Graphics g) {
        g.drawImage(photo,cornerX,cornerY,sizeX,sizeY,null);
    }


    //**********  GAMEPLAY  ***************************************************************
    private int moveSpeed = 3;
    private int attackSpeed = 250; //ms
        long lastAttack = System.currentTimeMillis();
    private int bulletSpeed = 10;
    private boolean useMouse = false;
    private int health = 1;
    private int live = 5;
    private boolean dead = false;
    public long lastDead = 0;
    public boolean justDead = false;



    //**********  COPY METHOR  ***************************************************************


    
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

    public void setPhoto(Image image) {
        photo = image;
    }

    public void setMoveSpeed(int v) {moveSpeed = v;}

    public void setLive(int v) {live = v;}

    public void setUseMouse(boolean v) {useMouse = v;}

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
    
    public Image getPhoto() {return photo;}

    public int getMoveSpeed() {return moveSpeed;}

    public boolean getUseMouse() {return useMouse;}

    public int getLive() {return live;}

    public int getHealth() {return health;}

    public boolean getDead() {return dead;}

    
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

    //********** ATTACK OF PLANES  ***************************************************************
    public void attack() {
        if (dead) return;
        long now = System.currentTimeMillis();
        if (now - lastAttack <= attackSpeed) return;
        lastAttack = now;

        Bullet bullet = new Bullet(false);
        bullet.setMoveSpeed(bulletSpeed);
        bullet.setX(x);
        bullet.setY(y - sizeY/2 - 15);

        int n = ++Object.countBullet;
        Object.bullets[n-1] = bullet;
    }

    //********** RESPAWNING  ***************************************************************
    public void respawn() {
        if (live==0) return;
        dead = false;
        live--;
        setX(BACKGROUND_WIDTH/2);
        setY(BACKGROUND_HEIGHT*4/5);
        setHealth(1000000000);
    }

    //********** DESTRUCTOR  ***************************************************************
    public boolean destruct() {
        if (!justDead) return false;
        justDead = false;
        return true;
    }

    //********** MAIN FUNCTION
    public MyPlane(int X,int Y,Image Photo) {
        counter++;
        x = X;
        y = Y;
        photo = Photo;
    }
}
