package controllers;

import models.GameObject;

import java.awt.*;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public interface Colliable {
    boolean canCollide = true;
    GameObject getCollisionObject();
    void onCollide(Colliable col);
}
