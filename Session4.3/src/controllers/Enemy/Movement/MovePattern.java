package controllers.Enemy.Movement;

import controllers.PlaneController;
import models.GameObject;
import models.Plane;

import java.util.Random;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class MovePattern {

    public MovePattern() {

    }

    public static MovePattern newMovePattern(MovePatternType tp) {
        if (tp==MovePatternType.DOWN) return new MovePatternDown();
        if (tp==MovePatternType.RANDOM) return new MovePatternRandom();
        if (tp==MovePatternType.ZIGZAG) return new MovePatternZigzag();
        return new MovePatternDown();
    }


    public void move(GameObject go) {

    }
}
