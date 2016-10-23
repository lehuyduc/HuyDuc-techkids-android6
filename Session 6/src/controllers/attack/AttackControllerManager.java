package controllers.attack;

import controllers.ControllerManager;
import models.GameObject;

import java.util.Vector;

/**
 * Created by Le Huy Duc on 21/10/2016.
 */
public class AttackControllerManager {
    private Vector<AttackController> attackControllers;

    public AttackControllerManager() {
        attackControllers = new Vector<>();
    }


    //**********  FUNCTIONS******************************************************************
    public int size() {
        return attackControllers.size();
    }

    public void add(AttackController attackController) {
        attackControllers.add(attackController);
    }

    public void remove(AttackController attackController) {
        attackControllers.remove(attackController);
    }

    public void clear() {
        attackControllers.clear();
    }


    //**********  RUN  ******************************************************************
    public void run(GameObject go,ControllerManager bulletController) {
        for (AttackController attackController : attackControllers)
            attackController.run(go,bulletController);
    }
}
