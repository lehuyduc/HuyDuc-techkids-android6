package controllers.enemy;

import controllers.*;
import controllers.Movement.*;
import controllers.attack.AttackController;
import controllers.attack.BulletType;
import models.EnemyPlane;
import models.GameObject;
import utilities.Utils;
import views.GameView;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class EnemyPlaneController extends SingleController implements Colliable {

    private ControllerManager bulletManager;
    private MovePattern movePattern;
    private AttackController attackController;
    private EnemyPlaneType enemyPlaneType;
    protected boolean isBoss = false;


    //**********  CONSTRUCTOR/DESTRUCTOR ******************************************************************
    void checkView() {
        if (enemyPlaneType==null) enemyPlaneType = EnemyPlaneType.WHITE;
        if (enemyPlaneType==EnemyPlaneType.WHITE) gameView.setImage("enemy_plane_white_2.png");
        if (enemyPlaneType==EnemyPlaneType.YELLOW) gameView.setImage("enemy_plane_yellow_2.png");
        if (enemyPlaneType==EnemyPlaneType.PHOENIX) gameView.setImage("phoenix.png");
    }

    void checkDefault() {
        checkView();
        if (bulletManager==null) bulletManager = new ControllerManager();

        if (enemyPlaneType==EnemyPlaneType.WHITE) {
            gameObject.setHealth(100);
            gameObject.setAttackSpeed(2000);
            gameObject.setMoveSpeed(1);
            movePattern = new MovePatternDown();
            attackController = AttackController.create(BulletType.ENEMY_BULLET,MovePatternType.DOWN);
        }

        if (enemyPlaneType==EnemyPlaneType.YELLOW) {
            gameObject.setHealth(150);
            gameObject.setAttackSpeed(1900);
            gameObject.setMoveSpeed(1);
            movePattern = new MovePatternFollow(true);
            attackController = AttackController.create(BulletType.ENEMY_BULLET,MovePatternType.AIM);
        }

        if (enemyPlaneType==EnemyPlaneType.PHOENIX) {
            gameObject.setSizeX(80); gameObject.setSizeY(80);
            gameObject.setHealth(220);
            gameObject.setAttackSpeed(1900);
            gameObject.setMoveSpeed(1);
            movePattern = new MovePatternRandom();
            attackController = AttackController.create(BulletType.PHOENIX,MovePatternType.FOLLOW);
        }


        if (movePattern==null) movePattern = new MovePatternRandom();
        if (attackController==null) attackController = AttackController.create(
                BulletType.ENEMY_BULLET,
                MovePatternType.DOWN
        );

        if (gameObject.getMoveSpeed()==0) gameObject.setMoveSpeed(1);
        if (gameObject.getAttackSpeed()==0) gameObject.setAttackSpeed(2000);
        if (gameObject.getHealth()==1) gameObject.setHealth(100);
        if (enemyPlaneType==EnemyPlaneType.YELLOW) gameObject.setHealth(200);
        if (enemyPlaneType==EnemyPlaneType.PHOENIX) {
            gameObject.setSizeX(80);
            gameObject.setSizeY(80);
            gameObject.setHealth(250);
            gameObject.setDamage(125);
        }
    }

    protected EnemyPlaneController(GameObject go, GameView gv) {
        super(go, gv);
        checkDefault();
    }

    private EnemyPlaneController(int x,int y, EnemyPlaneType type) {
        super(new EnemyPlane(x,y), new ImageView("enemy_plane_white_2.png"));
        enemyPlaneType = type;
        checkDefault();
    }

    public static EnemyPlaneController create(int x,int y,EnemyPlaneType enemyPlaneType) {
        return new EnemyPlaneController(x,y,enemyPlaneType);
    }


    public boolean deathEffect() {
        if (deathEffect) return false;
        deathEffect = true;
        GameObject go = gameObject;
        ExplosionControllerManager.instance.add
                (new ExplosionController(go.getX(),go.getY(),go.getSizeX(),go.getSizeY()));
        return true;
    }


    public boolean deleteNow() {
        return gameObject.deleteNow() && bulletManager.deleteNow();
    }

    //**********  GETTER/SETTER *****************************************************************
    public boolean isBoss() {return isBoss;}


    //**********  COLLISION ******************************************************************
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof PlaneController) {
            GameObject plane = col.getCollisionObject();
            plane.takeDamage(getHealth()*2);
        }
        if (col instanceof BulletController) {
            GameObject bullet = col.getCollisionObject();
            bullet.takeDamage(1000);
        }
    }

    //**********  MVC ******************************************************************

    private long lastAttack = 0;
    private void attack() {
        if (gameObject.getAttackSpeed()==0) return;
        long now = System.currentTimeMillis();
        if (now - lastAttack < gameObject.getAttackSpeed()) return;
        lastAttack = now;

        GameObject go = gameObject;
        attackController.run(gameObject,bulletManager);
    }

    public void draw(Graphics g) {
        if (!gameObject.getDead()) gameView.drawImage(g,gameObject);
        bulletManager.draw(g);
    }

    public void run() {
        bulletManager.run();
        if (gameObject.getDead()) deathEffect();
        if (gameObject.getDead()) return;

        if (movePattern!=null) movePattern.move(gameObject);
        attack();
    }
}
