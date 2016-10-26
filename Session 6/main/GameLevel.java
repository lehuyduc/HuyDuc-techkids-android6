package main;

import controllers.managers.CollisionManager;
import controllers.enemy.BossController;
import controllers.enemy.EnemyPlaneController;
import controllers.managers.EnemyPlaneControllerManager;
import controllers.enemy.EnemyPlaneType;
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
    static public int currentLevel = 0;
    static boolean phase1 = false, phase2 = false, phase3 = false;
    private static BossController boss1;

    private GameLevel() {

    }

    public static void init() {
        for (int i=0;i<=MAX_LEVEL;i++) level[i] = false;
    }

    private void spamBot(int n, EnemyPlaneType enemyPlaneType) {
        int d = GameWindow.BACKGROUND_WIDTH / (n+1);
        for (int i=0;i<n;i++) {
            EnemyPlaneControllerManager.instance.add(EnemyPlaneController.create(i*200+100,25,enemyPlaneType));
        }
    }

    private void level1() {
        if (EnemyPlaneControllerManager.instance.size()>0) return;
        if (!phase1) {spamBot(0,EnemyPlaneType.WHITE); phase1 = true; return;}
        if (!phase2) {spamBot(0,EnemyPlaneType.WHITE); phase2 = true; return;}
        if (!phase3) {spamBot(4,EnemyPlaneType.YELLOW); phase3 = true; return;}
        level[1] = true;
        phase1 = phase2 = phase3 = false;
    }

    private void level2() {
        if (EnemyPlaneControllerManager.instance.size() > 0) return;
        if (!phase1) {spamBot(4,EnemyPlaneType.YELLOW); phase1 = true; return;}
        if (!phase2) {spamBot(3,EnemyPlaneType.YELLOW); spamBot(2,EnemyPlaneType.PHOENIX); phase2 = true; return;}
        if (!phase3) {spamBot(3,EnemyPlaneType.YELLOW); spamBot(4,EnemyPlaneType.PHOENIX); phase3 = true; return;}
        level[2] = true;
        phase1 = phase2 = phase3 = false;
    }

    private synchronized void level3() {
        if (!phase1) {
            boss1 = new BossController(new Boss(600,0), new ImageView("PCarrierH.png"));
            EnemyPlaneControllerManager.instance.add(boss1);
            CollisionManager.instance.add(boss1);
            phase1 = true;
        }
        if (boss1.getDead()) {
            level[3] = true;
            phase1 = phase2 = phase3 = false;
        }
    }

   public synchronized void run() {
        if (!level[1]) {level1(); return;}
        if (!level[2]) {level2(); return;}
        if (!level[3]) {level3(); return;}
    }

    public static final GameLevel instance = new GameLevel();
}
