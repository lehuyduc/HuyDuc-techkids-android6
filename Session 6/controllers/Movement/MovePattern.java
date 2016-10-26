package controllers.Movement;

import models.GameObject;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class MovePattern {

    public MovePattern() {

    }

    public static MovePattern create(MovePatternType tp) {
        if (tp==MovePatternType.DOWN) return new MovePatternDown();
        if (tp==MovePatternType.RANDOM) return new MovePatternRandom();
        if (tp==MovePatternType.ZIGZAG) return new MovePatternZigzag();
        if (tp==MovePatternType.AIM) return new MovePatternAim();
        if (tp==MovePatternType.FOLLOW) return new MovePatternFollow();
        if (tp==MovePatternType.UP) return new MovePatternUp();
        return new MovePatternRandom();
    }

    public void move(GameObject go) {

    }
}
