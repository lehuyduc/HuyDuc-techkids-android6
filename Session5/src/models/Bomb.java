package models;

import controllers.Colliable;
import main.GamePlay;
import main.GameWindow;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class Bomb extends GameObject  {

    static private final int SIZEX = 35, SIZEY = 35;
    private long firstAppear;

    public Bomb(int x,int y) {
        super(x,y,SIZEX,SIZEY);
        firstAppear = System.currentTimeMillis();
    }

    public boolean deleteNow() {return oos() || getDead();}

    public boolean getDead() {
        long now = System.currentTimeMillis();
        if (now - firstAppear >= GamePlay.bombLife) dead = true;
        return oos() || dead;
    }
}
