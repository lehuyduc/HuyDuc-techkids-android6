package controllers;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class ExplosionSingleControllerManager extends SingleControllerManager {

    private ExplosionSingleControllerManager() {

    }

    @Override
    public synchronized void run() {
        super.run();
    }

    public static final ExplosionSingleControllerManager instance = new ExplosionSingleControllerManager();
}
