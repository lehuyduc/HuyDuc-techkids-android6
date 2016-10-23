package controllers.attack;

import controllers.BulletController;
import controllers.ControllerManager;
import controllers.Movement.MovePattern;
import controllers.Movement.MovePatternDown;
import controllers.Movement.MovePatternType;
import controllers.SingleController;
import controllers.enemy.EnemyBulletController;
import models.EnemyBullet;
import models.GameObject;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class AttackController {

    private MovePatternType bulletPatternType;
    private BulletType bulletType;

    //**********  CONSTRUCTOR ******************************************************************
    private AttackController() {

    }

    public static AttackController create(BulletType bulletType, MovePatternType bulletPatternType) {
        AttackController attackController = new AttackController();
        attackController.bulletType = bulletType;
        attackController.bulletPatternType = bulletPatternType;
        return attackController;
    }


    //**********  GETTER/ SETTER******************************************************************


    public MovePatternType getBulletPatternType() {
        return bulletPatternType;
    }

    public void setBulletPatternType(MovePatternType bulletPatternType) {
        this.bulletPatternType = bulletPatternType;
    }

    public BulletType getBulletType() {
        return bulletType;
    }

    public void setBulletType(BulletType bulletType) {
        this.bulletType = bulletType;
    }


    //**********  ATTACK ******************************************************************
    public void run(GameObject go, ControllerManager bulletManager) {
        int y;
        SingleController bc;
        if (go.getEnemy()) {
            y = go.getY() + go.getSizeY()/2;
            bc = EnemyBulletController.create(go.getX(),y,bulletType,bulletPatternType);
        }
        else {
            y = go.getY() - go.getSizeY()/2;
            bc = BulletController.create(go.getX(),y,bulletType,bulletPatternType);
        }
        bulletManager.add(bc);
    }

}
