package controllers.Enemy.Movement;

import models.GameObject;
import models.GameVector;

import java.util.Random;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class MovePatternZigzag implements MovePattern {
    int direction = 0;
    long lastRand = 0;

    @Override
    public void move(GameObject go) {
        GameVector u = new GameVector();
        u.y = go.getMoveSpeed();

        long now = System.currentTimeMillis();
        if (now - lastRand >= 1000) {
            Random rd = new Random();
            direction = rd.nextInt(2)+1;
            lastRand = now;
        }

        if (direction==1) u.x = go.getMoveSpeed();
        else u.x = -go.getMoveSpeed();

        go.move(u);
    }

}
