package controllers.enemy;

import controllers.ControllerManager;
import controllers.SingleController;
import controllers.enemy.EnemyPlaneControllerManager;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 15/10/2016.
 */
public class BackManager extends ControllerManager {

    private BackManager() {
        singleControllers = new Vector<>();
    }

    @Override
    public synchronized void draw(Graphics g) {

    }

    @Override
    public synchronized void run() {
        Iterator<SingleController> it = singleControllers.iterator();
        while (it.hasNext()) {
            SingleController singleController = it.next();
            EnemyPlaneControllerManager.instance.add(singleController);
            it.remove();
        }
        System.out.println(size());
    }

    public static final BackManager instance = new BackManager();
}
