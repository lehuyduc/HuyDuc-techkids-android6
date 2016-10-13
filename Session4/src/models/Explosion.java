package models;

import controllers.SingleController;
import main.GameWindow;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class Explosion extends GameObject {

    private static final int SIZEX = 50, SIZEY = 50;
    private long firstExplosion;

    public Explosion(int x,int y) {
        super(x,y);
        setSizeX(SIZEX);
        setSizeY(SIZEY);
        firstExplosion = System.currentTimeMillis();
    }

    public Explosion(int x,int y,int sx,int sy) {
        super(x,y,sx,sy);
        firstExplosion = System.currentTimeMillis();
    }

    public boolean deleteNow() {
        long now = System.currentTimeMillis();
        return (now - firstExplosion) >= 6*GameWindow.animationDelay;
    }

    void run() {

    }
}
