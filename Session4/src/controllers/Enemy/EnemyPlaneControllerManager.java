package controllers.Enemy;

import controllers.ControllerManager;
import controllers.SingleController;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class EnemyPlaneControllerManager extends ControllerManager {

    private EnemyPlaneControllerManager() {

    }

    public void takeDamage(int v) {
        for (SingleController singleController : singleControllers)
            singleController.getGameObject().takeDamage(v);
    }

    public static final EnemyPlaneControllerManager instance = new EnemyPlaneControllerManager();
}
