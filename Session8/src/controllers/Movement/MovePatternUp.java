package controllers.Movement;

import models.GameObject;
import models.GameVector;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class MovePatternUp extends MovePattern {

    public void move(GameObject go) {
        GameVector u = new GameVector(0,-go.getMoveSpeed());
        go.move(u);
    }
}
