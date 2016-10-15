package controllers;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class ExplosionControllerManager extends ControllerManager {

    private ExplosionControllerManager() {

    }

    @Override
    public synchronized void run() {
        super.run();
    }

    public static final ExplosionControllerManager instance = new ExplosionControllerManager();
}
