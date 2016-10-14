package controllers;

import controllers.Enemy.EnemyPlaneController;
import controllers.Enemy.EnemyPlaneControllerManager;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class GameLevel {

    public GameLevel() {

    }

    public static void level1() {
        if (EnemyPlaneControllerManager.instance.size()==0) {
            for (int i=0;i<8;i++) EnemyPlaneControllerManager.instance.add
                    (new EnemyPlaneController(i*100+100,100));
        }
    }
}
