package controllers.Movement;

import models.GameObject;
import models.GameVector;

import java.util.Random;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class MovePatternRandom extends MovePattern {

    int direction = 0;
    long lastRand = 0;
    GameVector u = new GameVector();

    @Override
    public void move(GameObject go) {
        u.y = go.getMoveSpeed();

        long now = System.currentTimeMillis();
        if (now - lastRand <= 3500) {
            Random rd = new Random();
            direction = rd.nextInt(3);
        }

        if (direction==0) u.x = 0;
        else if (direction==1) u.x = go.getMoveSpeed();
        else u.x = -go.getMoveSpeed();

        go.move(u);
    }
}
