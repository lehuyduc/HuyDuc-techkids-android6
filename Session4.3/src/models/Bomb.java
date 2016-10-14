package models;

import controllers.Colliable;
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

    public boolean deleteNow() {return oos() || dead;}

    public boolean getDead() {
        long now = System.currentTimeMillis();
        return (now - firstAppear >= GameWindow.bombLife) || oos() || dead;
    }
}
