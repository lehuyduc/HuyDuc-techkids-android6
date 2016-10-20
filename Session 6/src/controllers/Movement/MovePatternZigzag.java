package controllers.Movement;

import main.GameConfig;
import models.GameObject;
import models.GameVector;

import java.util.Random;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class MovePatternZigzag extends MovePattern {
    int direction = 0;
    long lastRand = 0;

    @Override
    public void move(GameObject go) {
        GameVector u = new GameVector();
        u.y = go.getMoveSpeed();

        long now = System.currentTimeMillis();
        if (now - lastRand >= 2500) {
            Random rd = new Random();
            direction = rd.nextInt(2)+1;
            lastRand = now;
        }

        if (direction==1) u.x = go.getMoveSpeed();
        else u.x = -go.getMoveSpeed();

        if (go.getX() <= 100) {u.x = go.getMoveSpeed(); lastRand = (long)(1000000000)*(long)(1000000000);}
        if (go.getX() >= GameConfig.DEFAULT_BACKGROUND_WIDTH)
            {u.x = -go.getMoveSpeed(); lastRand = (long)(1000000000)*(long)(1000000000);}

        go.move(u);
    }

}
