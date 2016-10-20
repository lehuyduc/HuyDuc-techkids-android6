package controllers.attack;

import controllers.ControllerManager;
import controllers.Movement.MovePattern;
import controllers.Movement.MovePatternDown;
import controllers.Movement.MovePatternType;
import controllers.enemy.EnemyBulletController;
import models.GameObject;

import java.awt.*;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class AttackController {

    private MovePatternType bulletPatternType;
    private Image bulletImage = null;

    public AttackController() {
        bulletPatternType = MovePatternType.DOWN;
    }

    public AttackController(MovePatternType bulletPatternType) {
        this.bulletPatternType = bulletPatternType;
    }

    public void attack(GameObject go, ControllerManager bulletManager) {
        int y;
        if (go.getEnemy()) y = go.getY() + go.getSizeY()/2;
        else y = go.getY() - go.getSizeY()/2;
        EnemyBulletController ebc = new EnemyBulletController(go.getX(), y, bulletPatternType);
        bulletManager.add(ebc);
    }
}
