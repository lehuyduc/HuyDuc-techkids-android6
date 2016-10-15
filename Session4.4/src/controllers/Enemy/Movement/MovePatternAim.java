package controllers.Enemy.Movement;

import models.GameObject;
import models.GameVector;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class MovePatternAim extends MovePattern {

    private int dx=0, dy=0;

    public MovePatternAim(int x,int y) {
        dx = x;
        dy = y;
    }

    public MovePatternAim(int x1,int y1,int x2,int y2) {
        dx = x2-x1;
        dy = y2-y1;
    }

    @Override
    public void move(GameObject go) {
        double v = go.getMoveSpeed();

        double alpha = Math.atan2(dy,dx);

        dx = (int)Math.round(v*Math.cos(alpha));
        dy = (int)Math.round(v*Math.sin(alpha));

        go.move(new GameVector(dx,dy));
    }
}
