package controllers;

import controllers.Enemy.EnemyPlaneControllerManager;

import java.awt.*;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class ControllerController {

    private ControllerController() {

    }

    public synchronized void draw(Graphics g) {
        PlaneController.instance1.draw(g);
        PlaneController.instance2.draw(g);
        EnemyPlaneControllerManager.instance.draw(g);
        ExplosionControllerManager.instance.draw(g);
        CollisionManager.instance.draw(g);
    }

    public synchronized void run() {
        PlaneController.instance1.run();
        PlaneController.instance2.run();
        EnemyPlaneControllerManager.instance.run();
        ExplosionControllerManager.instance.run();
        CollisionManager.instance.run();
    }

    public static final ControllerController instance = new ControllerController();
}
