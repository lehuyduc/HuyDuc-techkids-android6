package controllers;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class ExplosionControllerManager extends ControllerManager {

    public ExplosionControllerManager() {
        singleControllers = new Vector<>();
    }

    public static final ExplosionControllerManager instance = new ExplosionControllerManager();
}
