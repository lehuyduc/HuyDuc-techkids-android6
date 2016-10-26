package controllers.attack;

import controllers.*;
import controllers.Movement.MovePatternType;
import controllers.enemy.EnemyBulletController;
import controllers.managers.ControllerManager;
import models.GameObject;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class AttackController {

    private MovePatternType bulletPatternType;
    private BulletType bulletType;
    private int bulletLevel;

    //**********  CONSTRUCTOR ******************************************************************
    private AttackController() {

    }

    public static AttackController create(BulletType bulletType, MovePatternType bulletPatternType) {
        AttackController attackController = new AttackController();
        attackController.bulletType = bulletType;
        attackController.bulletPatternType = bulletPatternType;
        attackController.bulletLevel = 1;
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

    public void powerUp(int v) {bulletLevel += v;}


    //**********  ENEMY ATTACK ******************************************************************

    public void enemyAttack(GameObject go, ControllerManager bulletManager) {
        int y = go.getY() + go.getSizeY()/2;
        SingleController bc = EnemyBulletController.create(go.getX(),y,bulletType,bulletPatternType);
        bulletManager.add(bc);
    }


    //**********  ENEMY ATTACK ******************************************************************
    private void playerBullet(GameObject go, ControllerManager bulletManager) {
        int y = go.getY()-go.getSizeY()/2;

        SingleController bulletMid = BulletController.create(go.getX(),y,
                BulletType.PLAYER_BULLET,MovePatternType.UP);

        SingleController bulletMidLeft = BulletController.create(go.getX()-go.getSizeX()/4, y,
                BulletType.PLAYER_BULLET,MovePatternType.UP);

        SingleController bulletMidRight = BulletController.create(go.getX()+go.getSizeX()/4, y,
                BulletType.PLAYER_BULLET,MovePatternType.UP);

        SingleController bulletLeft = BulletController.create(go.getX()-go.getSizeX()/2, y,
                BulletType.PLAYER_BULLET_LEFT,MovePatternType.AIM);

        SingleController bulletRight = BulletController.create(go.getX()+go.getSizeX()/2, y,
                BulletType.PLAYER_BULLET_RIGHT,MovePatternType.AIM);

        if (bulletLevel==1) bulletManager.add(bulletMid);
        if (bulletLevel>=2) {bulletManager.add(bulletMidLeft); bulletManager.add(bulletMidRight);}
        if (bulletLevel>=3) bulletManager.add(bulletLeft);
        if (bulletLevel>=4) bulletManager.add(bulletRight);
    }

    public void playerAttack(GameObject go, ControllerManager bulletManager) {
        if (bulletType==BulletType.PLAYER_BULLET) playerBullet(go,bulletManager);
    }

    public void run(GameObject go, ControllerManager bulletManager) {
        int y;
        SingleController bc;
        if (go.getEnemy()) {enemyAttack(go,bulletManager); return;}


        playerAttack(go,bulletManager);
    }

}
