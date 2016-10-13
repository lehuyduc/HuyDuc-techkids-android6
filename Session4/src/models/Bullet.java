package models;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class Bullet extends GameObject {

    protected static int SIZEX = 13, SIZEY = 30;

    public Bullet(int x,int y,boolean isEnemy) {
        super(x,y);
        enemy = isEnemy;
        setSizeX(SIZEX); setSizeY(SIZEY);
        if (enemy) setMoveSpeed(6); else setMoveSpeed(10);
        setDamage(50);
    }

    public boolean needDelete() {
        return oos() || dead;
    }
}
