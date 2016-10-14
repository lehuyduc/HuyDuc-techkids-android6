package models;

import controllers.Colliable;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class Bomb extends GameObject  {

    static private final int SIZEX = 35, SIZEY = 35;

    public Bomb(int x,int y) {
        super(x,y,SIZEX,SIZEY);
    }
}
