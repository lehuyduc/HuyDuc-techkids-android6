package controllers;

import controllers.enemy.BackManagerSingle;
import controllers.managers.CollisionManager;
import controllers.managers.EnemyPlaneControllerManager;
import controllers.gifts.GiftManager;
import controllers.managers.ExplosionControllerManager;

import java.awt.*;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class ControllerController {


    private ControllerController() {

    }

    private boolean inited = false;

    public synchronized void init() {
        CollisionManager.instance.clear();
        ExplosionControllerManager.instance.clear();
        EnemyPlaneControllerManager.instance.clear();
        GiftManager.instance.clear();
        PlaneController.instance1.checkDefault();
        PlaneController.instance2.checkDefault();
    }

    public synchronized void draw(Graphics g) {
        PlaneController.instance1.draw(g);
        PlaneController.instance2.draw(g);
        GiftManager.instance.draw(g);
        EnemyPlaneControllerManager.instance.draw(g);
        ExplosionControllerManager.instance.draw(g);
        CollisionManager.instance.draw(g);
    }

    public synchronized void run() {
        PlaneController.instance1.run();
        PlaneController.instance2.run();
        GiftManager.instance.run();
        BackManagerSingle.instance.run();
        EnemyPlaneControllerManager.instance.run();
        ExplosionControllerManager.instance.run();
        CollisionManager.instance.run();
    }

    public static final ControllerController instance = new ControllerController();
}
