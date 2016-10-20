package models;

import controllers.Colliable;
import models.GameObject;
import utilities.Utils;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 15/10/2016.
 */
public class Boss extends GameObject {

    private static final int SIZEX = 132, SIZEY = 208;

    @Override
    public boolean collide(GameObject go) {
        return false;
    }

    public Boss(int x,int y) {
        super(x,y,SIZEX,SIZEY);
    }

    public boolean deleteNow() {
        return oos() || dead;
    }
}
