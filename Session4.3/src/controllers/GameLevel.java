package controllers;

import controllers.Enemy.EnemyPlaneController;
import controllers.Enemy.EnemyPlaneControllerManager;
import controllers.Enemy.Movement.MovePatternType;

import java.util.Random;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class GameLevel {

    private static Random rd = new Random();

    public GameLevel() {

    }

    public static void level1() {
        if (EnemyPlaneControllerManager.instance.size()>0) return;
        for (int i=0;i<9;i++) {
            MovePatternType planeMove;
            MovePatternType bulletMove;
            if (i%3==0) planeMove = MovePatternType.RANDOM;
            else planeMove = MovePatternType.ZIGZAG;

            int t = rd.nextInt(4);
            if (t==0) bulletMove = MovePatternType.AIM;
            else bulletMove = MovePatternType.DOWN;

            EnemyPlaneControllerManager.instance.add
                    (new EnemyPlaneController(i*100+100, 0,planeMove,bulletMove));
        }
    }
}
