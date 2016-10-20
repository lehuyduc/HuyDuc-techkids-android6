package controllers.enemy;

import controllers.SingleControllerManager;
import controllers.SingleController;

import java.awt.*;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class EnemyPlaneControllerManager extends SingleControllerManager {

    private EnemyPlaneControllerManager() {
        super();
    }

    public void takeDamage(int v) {
        for (SingleController singleController : singleControllers)
            singleController.getGameObject().takeDamage(v);
    }

    public synchronized void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public synchronized void run() {
        super.run();
    }

    public static final EnemyPlaneControllerManager instance = new EnemyPlaneControllerManager();
}
