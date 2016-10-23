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

    public MovePatternZigzag() {
        Random rd = new Random();
        direction = rd.nextInt(2);
    }

    @Override
    public void move(GameObject go) {
        GameVector u = new GameVector();
        u.y = go.getMoveSpeed();

        if (direction==1) u.x = go.getMoveSpeed();
        else u.x = -go.getMoveSpeed();

        if (go.getX() <= 100) u.x = go.getMoveSpeed();
        if (go.getX() >= GameConfig.DEFAULT_BACKGROUND_WIDTH) u.x = -go.getMoveSpeed();

        go.move(u);
    }

}
