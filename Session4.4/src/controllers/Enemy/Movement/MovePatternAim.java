package controllers.Enemy.Movement;

import models.GameObject;
import models.GameVector;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class MovePatternAim extends MovePattern {

    private int dx=0, dy=0;
    private Point target = new Point(-1,-1);
    private boolean chase = true;

    public MovePatternAim(int x,int y) {
        target = new Point(x,y);
    }

    @Override
    public void move(GameObject go) {
        double v = go.getMoveSpeed();

        if (chase) {
            if (Utils.distance(target,new Point(go.getX(),go.getY())) <= 400) chase = false;
            dx = target.x - go.getX();
            dy = target.y - go.getY();
        }
        double alpha = Math.atan2(dy,dx);

        int x,y;
        x = (int)Math.round(v*Math.cos(alpha));
        y = (int)Math.round(v*Math.sin(alpha));

        go.move(new GameVector(x,y));
    }
}
