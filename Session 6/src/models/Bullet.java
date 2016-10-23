package models;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class Bullet extends GameObject {

    protected static int SIZEX = 13, SIZEY = 30;

    public Bullet(int x,int y) {
        super(x,y,SIZEX,SIZEY);
        setMoveSpeed(10);
        enemy = false;
    }


    public boolean deleteNow() {
        return oos() || dead;
    }
}
