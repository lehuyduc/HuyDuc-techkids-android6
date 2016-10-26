package models;

import controllers.Colliable;
import main.GameConfig;
import main.GamePlay;
import main.GameWindow;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class Bomb extends GameObject  {

    static private final int SIZEX = 35, SIZEY = 35;
    private long firstAppear;
    private int damageRadius = 600;

    public Bomb(int x,int y) {
        super(x,y,SIZEX,SIZEY);
        firstAppear = System.currentTimeMillis();
    }

    public int getDamageRadius() {return damageRadius;}

    public boolean deleteNow() {return oos() || getDead();}

    public boolean getDead() {
        long now = System.currentTimeMillis();
        if (now - firstAppear >= GameConfig.bombLife) dead = true;
        return oos() || dead;
    }
}
