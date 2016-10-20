package controllers.Movement;

import models.GameObject;
import models.GameVector;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class MovePatternDown extends MovePattern {

    @Override
    public void move(GameObject go) {
        GameVector u = new GameVector(0,go.getMoveSpeed());
        go.move(u);
    }

}
