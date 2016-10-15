package controllers.Enemy.Movement;

import models.GameObject;
import models.GameVector;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class MovePatternFollow extends MovePattern {
    private GameObject target;
    private int dx, dy;
    private boolean chase = true;
    private long firstChase = 0;
    public static int count = 0;

    private MovePatternFollow() {

    }

    public MovePatternFollow(GameObject go) {
        target = go;
        firstChase = System.currentTimeMillis();
    }

    @Override
    public void move(GameObject go) {
        int v = go.getMoveSpeed();
        if (Utils.distance(new Point(target.getX(),target.getY()),
                                     new Point(go.getX(),go.getY())) <= 245) chase = false;
        long now = System.currentTimeMillis();
        if (chase && now - firstChase <= 10000) {
            dx = target.getX() - go.getX();
            dy = target.getY() - go.getY();
        }
        double alpha = Math.atan2(dy,dx);

        dx = (int)Math.round(v*Math.cos(alpha));
        dy = (int)Math.round(v*Math.sin(alpha));
        go.move(new GameVector(dx,dy));
    }

}
