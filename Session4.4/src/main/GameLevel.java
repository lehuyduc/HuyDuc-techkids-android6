package main;

import controllers.CollisionManager;
import controllers.Enemy.BossController;
import controllers.Enemy.EnemyPlaneController;
import controllers.Enemy.EnemyPlaneControllerManager;
import controllers.Enemy.Movement.MovePatternType;
import models.Boss;
import views.ImageView;

import java.util.Random;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class GameLevel {

    private static Random rd = new Random();
    static int MAX_LEVEL = 3;
    static public boolean[] level = new boolean[50];
    static boolean phase1 = false, phase2 = false, phase3 = false;
    BossController boss1;

    private GameLevel() {

    }

    public static void init() {
        for (int i=0;i<=MAX_LEVEL;i++) level[i] = false;
    }

    private void spamBot(int n,boolean bulletAll, boolean yellow) {
        int d = GameWindow.BACKGROUND_WIDTH / (n+1);
        for (int i=0;i<n;i++) {
            EnemyPlaneControllerManager.instance.add(new
                    EnemyPlaneController(
                            100+d*i,40,MovePatternType.getRandom(false),MovePatternType.getRandom(bulletAll),yellow));
        }
    }

    private void level1() {
        if (EnemyPlaneControllerManager.instance.size()>0) return;
        if (!phase1) {spamBot(3,true,false); phase1 = true; return;}
        if (!phase2) {MovePatternType.countFollow = 2; spamBot(5,true,false); phase2 = true; return;}
        if (!phase3) {MovePatternType.countFollow = 4; spamBot(7,true,false); phase3 = true; return;}
        level[1] = true;
        phase1 = phase2 = phase3 = false;
    }

    private void level2() {
        if (EnemyPlaneControllerManager.instance.size() > 0) return;
        if (!phase1) {MovePatternType.countFollow = 3; spamBot(3,true,true); phase1 = true; return;}
        if (!phase2) {MovePatternType.countFollow = 5; spamBot(4,true,true); phase2 = true; return;}
        if (!phase3) {MovePatternType.countFollow = 7; spamBot(6,true,true); phase3 = true; return;}
        level[2] = true;
        phase1 = phase2 = phase3 = false;
    }

    private synchronized void level3() {
        if (!phase1) {
            EnemyPlaneControllerManager.instance.add(BossController.instance);
            CollisionManager.instance.add(BossController.instance);

            phase1 = true;
        }
        if (BossController.instance.getDead()) {
            level[3] = true;
            phase1 = phase2 = phase3 = false;
        }
    }

   public synchronized void run() {
        if (!level[1]) {level1(); return;}
      //  if (!level[2]) {level2(); return;}
        if (!level[3]) {level3(); return;}
    }

    public static final GameLevel instance = new GameLevel();
}
