package controllers;

import models.GameObject;

import java.awt.*;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public interface Colliable {
    GameObject getCollisionObject();
    boolean getCanCollide();
    void setCanCollide(boolean v);
    void onCollide(Colliable col);
}
