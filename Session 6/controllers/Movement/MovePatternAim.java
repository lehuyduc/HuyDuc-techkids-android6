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
public class MovePatternAim extends MovePattern {

    private int dx=0, dy=0;
    private Point target = new Point(-1,-1);
    private boolean chase = true;
    private double alpha0 = 0;

    public MovePatternAim(double alpha) {
        alpha0 = -alpha;
        chase = false;
    }

    public MovePatternAim(int x,int y) {
        target = new Point(x,y);
    }

    public MovePatternAim() {
        Random rd = new Random();
        int tp = rd.nextInt(2)+1;
        GameObject plane;
        if (tp==1) plane = PlaneController.instance1.getGameObject();
        else plane = PlaneController.instance2.getGameObject();
        target = new Point(plane.getX(), plane.getY());
    }

    @Override
    public void move(GameObject go) {
        double v = go.getMoveSpeed();

        if (chase) {
            if (Utils.distance(target,new Point(go.getX(),go.getY())) <= 250) chase = false;
            dx = target.x - go.getX();
            dy = target.y - go.getY();
        }
        double alpha = Math.atan2(dy,dx);
        if (alpha0!=0) alpha = alpha0;

        int x,y;
        x = (int)Math.round(v*Math.cos(alpha));
        y = (int)Math.round(v*Math.sin(alpha));

        go.move(new GameVector(x,y));
    }
}
