package script;

import controllers.enemy.BossController;
import controllers.gifts.BombController;
import controllers.enemy.EnemyPlaneController;
import controllers.enemy.EnemyPlaneControllerManager;
import controllers.enemy.EnemyPlaneType;
import controllers.enemy.Movement.MovePatternType;
import controllers.SingleController;
import controllers.gifts.GiftManagerSingle;
import main.GamePlay;
import models.Boss;
import views.ImageView;

import java.util.Random;

/**
 * Created by Le Huy Duc on 17/10/2016.
 */
public class CREATE extends Script{
    SingleController singleController = null;

    int x = -1, y = -1;
    boolean xRand = false, yRand = false;
    MovePatternType movePatternType;
    MovePatternType bulletPatternType;
    EnemyPlaneType enemyPlaneType;

    private void input_enemy() {
        x = ScriptReader.instance.nextInt();
        y = ScriptReader.instance.nextInt();
        movePatternType = MovePatternType.valueOf(ScriptReader.instance.next());
        bulletPatternType = MovePatternType.valueOf(ScriptReader.instance.next());
        enemyPlaneType = EnemyPlaneType.valueOf(ScriptReader.instance.next());
        this.singleController = new EnemyPlaneController(x,y,movePatternType,bulletPatternType,enemyPlaneType);
    }

    private void input_bomb() {
        x = ScriptReader.instance.nextInt();
        y = ScriptReader.instance.nextInt();
        this.singleController = new BombController(x,y);
    }

    private void input_boss() {
        x = ScriptReader.instance.nextInt();
        y = ScriptReader.instance.nextInt();
        this.singleController = new BossController(new Boss(x,y), new ImageView("resources/PCarrierH.png"));
    }

    void checkNull() {
        if (x==-1) xRand = true;
        if (y==-1) yRand = true;
        if (movePatternType==null) movePatternType = MovePatternType.RANDOM;
        if (bulletPatternType==null) bulletPatternType = MovePatternType.FOLLOW;
        if (enemyPlaneType==null) enemyPlaneType = EnemyPlaneType.WHITE;
    }

    public CREATE() {
        String type = ScriptReader.instance.next();
        if (type.equals("ENEMY")) input_enemy();
        if (type.equals("BOMB")) input_bomb();
        if (type.equals("BOSS")) input_boss();
        checkNull();
    }

    public void preRun() {
        Random rd = new Random();
        if (xRand) x = rd.nextInt(GamePlay.BACKGROUND_WIDTH-200)+100;
        if (yRand) y = rd.nextInt(GamePlay.BACKGROUND_HEIGHT-200)+100;
    }

    public void run() {
        executed = true;
        preRun();

        if (this.singleController instanceof EnemyPlaneController &&
                !(singleController instanceof BossController)) {
            this.singleController = new EnemyPlaneController(x,y,movePatternType,bulletPatternType,enemyPlaneType);
            EnemyPlaneControllerManager.instance.add(singleController);
        }

        if (this.singleController instanceof BombController) {
            this.singleController = new BombController(x,y);
            GiftManagerSingle.instance.add(singleController);
        }

        if (this.singleController instanceof BossController) {
            this.singleController = new BossController(new Boss(x,y), new ImageView("resources/PCarrierH.png"));
            EnemyPlaneControllerManager.instance.add(singleController);
        }

        finished = true;
    }
}
