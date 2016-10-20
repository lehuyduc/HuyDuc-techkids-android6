package models;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class Plane extends GameObject {

    public static int SIZEX = 50, SIZEY = 50;

    public Plane(int x,int y) {
        super(x,y,SIZEX,SIZEY);
        enemy = false;
    }
}
