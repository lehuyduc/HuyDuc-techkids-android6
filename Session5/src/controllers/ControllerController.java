package controllers;

import controllers.enemy.BackManagerSingle;
import controllers.enemy.BossController;
import controllers.enemy.EnemyPlaneControllerManager;
import controllers.gifts.GiftManagerSingle;

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
        ExplosionSingleControllerManager.instance.clear();
        EnemyPlaneControllerManager.instance.clear();
        GiftManagerSingle.instance.clear();
        PlaneController.instance1.checkDefault();
        PlaneController.instance2.checkDefault();
        BossController.instance.checkDefault();
    }

    public synchronized void draw(Graphics g) {
        PlaneController.instance1.draw(g);
        PlaneController.instance2.draw(g);
        GiftManagerSingle.instance.draw(g);
        EnemyPlaneControllerManager.instance.draw(g);
        ExplosionSingleControllerManager.instance.draw(g);
        CollisionManager.instance.draw(g);
    }

    public synchronized void run() {
        PlaneController.instance1.run();
        PlaneController.instance2.run();
        GiftManagerSingle.instance.run();
        BackManagerSingle.instance.run();
        EnemyPlaneControllerManager.instance.run();
        ExplosionSingleControllerManager.instance.run();
        CollisionManager.instance.run();
    }

    public static final ControllerController instance = new ControllerController();
}
