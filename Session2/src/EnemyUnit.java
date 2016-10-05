import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

/**
 * Created by Le Huy Duc on 04/10/2016.
 */
public class EnemyUnit {
    //**********
    //********** NUMBER OF OBJECT
    public final static int MAX_PLANE = 10;
    public static int counter = 0;


    //********** IMAGE AND DRAWING  ***************************************************************
    private int x = 300, y = 200, lastX = 0, lastY = 0;
    private int sizeX = 50, sizeY = 50;
    private Image photo = null;


    //**********  GAMEPLAY  ***************************************************************
    private int moveSpeed = 5;
    private int health = 1;
    private int live = 1;
    private int weaponType;
    boolean enemy;

    //**********  SELFMADE FUNCTIONS  ***************************************************************
    public int abs(int x) {if (x>=0) return x; else return -x;}

    public int abs(int x,int y) {return abs(x-y);}

    //**********  SET OPERATOR  ***************************************************************
    public void setX(int v) {x = v;}

    public void setY(int v) {y = v;}

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

    public void setLive(int v) {live = v;}


    //**********  GET OPERATOR  ***************************************************************
    public int getX() {return x;}

    public int getY() {return y;}

    public int getSizeX() {return sizeX;}

    public int getSizeY() {return sizeY;}

    public Image getPhoto() {return photo;}

    public int getMoveSpeed() {return moveSpeed;}

    public int getLive() {return live;}


    //**********  MOVEMENT OF PLANES  ***************************************************************
    // 1 is down, 0 is up
    public void moveVertical(int down) {
        if (down==1) y += moveSpeed; else y -= moveSpeed;
    }

    // 0 is straight, 1 is right, 2 is left
    public void moveHorizontal(int side) {
        if (side==1) x += moveSpeed; else if (side==2) x -= moveSpeed;
    }

    public void move() {
    }
}
