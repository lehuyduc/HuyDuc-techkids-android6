package controllers.Movement;

import controllers.PlaneController;
import models.GameObject;
import models.GameVector;
import utilities.Utils;

import java.awt.*;
import java.util.Random;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class MovePatternFollow extends MovePattern {
    private GameObject target;
    private int dx, dy;
    private boolean chase = true, forever = false;
    private long firstChase = 0;
    public static int count = 0;

    public MovePatternFollow(GameObject go) {
        target = go;
        firstChase = System.currentTimeMillis();
    }

    public MovePatternFollow() {
        Random rd = new Random();
        int tp = rd.nextInt(2)+1;
        GameObject plane;
        if (tp==1) plane = PlaneController.instance1.getGameObject();
        else plane = PlaneController.instance2.getGameObject();
        target = plane;
        firstChase = System.currentTimeMillis();
    }

    public MovePatternFollow(boolean thisWillAlwaysTakeTrueBooleanValueBecauseLazyToReFactor) {
        Random rd = new Random();
        int tp = rd.nextInt(2)+1;
        GameObject plane;
        if (tp==1) plane = PlaneController.instance1.getGameObject();
        else plane = PlaneController.instance2.getGameObject();
        target = plane;
        firstChase = System.currentTimeMillis();
        forever = true;
    }


    @Override
    public void move(GameObject go) {
        int v = go.getMoveSpeed();
        if (Utils.distance(new Point(target.getX(),target.getY()),
                                     new Point(go.getX(),go.getY())) <= 190 && !forever) chase = false;
        long now = System.currentTimeMillis();
        if (chase && (now - firstChase <= 10000 || forever)) {
            dx = target.getX() - go.getX();
            dy = target.getY() - go.getY();
        }
        double alpha = Math.atan2(dy,dx);

        dx = (int)Math.round(v*Math.cos(alpha));
        dy = (int)Math.round(v*Math.sin(alpha));
        go.move(new GameVector(dx,dy));
    }

}
