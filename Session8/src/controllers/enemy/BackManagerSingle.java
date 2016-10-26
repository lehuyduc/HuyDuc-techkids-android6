package controllers.enemy;

import controllers.managers.ControllerManager;
import controllers.SingleController;
import controllers.managers.EnemyPlaneControllerManager;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 15/10/2016.
 */
public class BackManagerSingle extends ControllerManager {

    private BackManagerSingle() {
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
    }

    public static final BackManagerSingle instance = new BackManagerSingle();
}
