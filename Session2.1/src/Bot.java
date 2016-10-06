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
    public final static int MAX_BOT = 10;


    //********** IMAGE AND DRAWING  ***************************************************************
    protected int x = 300, y = 200, cornerX = 0, cornerY = 0;
    protected int sizeX = 50, sizeY = 50;
    protected Image photo = null;
    private int horizon = 0;


    //**********  GAMEPLAY  ***************************************************************
    private int moveSpeed = 5;
    private int health = 1;
    private int weaponType;

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


    //**********  GET OPERATOR  ***************************************************************
    public int getX() {return x;}

    public int getY() {return y;}

    public int getSizeX() {return sizeX;}

    public int getSizeY() {return sizeY;}

    public Image getPhoto() {return photo;}

    public int getMoveSpeed() {return moveSpeed;}



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
        moveVertical(1);
        Random rd = new Random();
        horizon = rd.nextInt(2);
        moveHorizontal(horizon);
    }

}
