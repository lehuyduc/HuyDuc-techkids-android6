package controllers.enemy;

import controllers.*;
import controllers.enemy.Movement.*;
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

    private SingleControllerManager bulletController;
    private MovePattern movePattern;
    private MovePatternType bulletPatternType;
    private EnemyPlaneType enemyPlaneType;
    private Image bulletImage = null;
    protected boolean isBoss = false;


    //**********  CONSTRUCTOR/DESTRUCTOR ******************************************************************
    void checkView() {
        if (enemyPlaneType==null) enemyPlaneType = EnemyPlaneType.WHITE;
        if (enemyPlaneType==EnemyPlaneType.WHITE) gameView.setImage("resources/enemy_plane_white_2.png");
        if (enemyPlaneType==EnemyPlaneType.YELLOW) gameView.setImage("resources/enemy_plane_yellow_2.png");
        if (enemyPlaneType==EnemyPlaneType.PHOENIX) {
            gameView.setImage("resources/phoenix.png");
            bulletImage = Utils.getImage("resources/phoenix_bullet3.png");
        }
        if (bulletImage == null) bulletImage = Utils.getImage("resources/enemy_bullet.png");
    }

    void checkDefault() {
        checkView();

        if (bulletController==null) bulletController = new SingleControllerManager();
        if (movePattern==null) movePattern = new MovePatternRandom();
        if (bulletPatternType==null) bulletPatternType = MovePatternType.DOWN;

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

    public EnemyPlaneController(GameObject go, GameView gv) {
        super(go, gv);
        checkDefault();
    }

    public EnemyPlaneController(int x,int y) {
        super(new EnemyPlane(x,y), new ImageView("resources/enemy_plane_white_2.png"));
        checkDefault();
    }

    public EnemyPlaneController(int x,int y,MovePatternType planeMove, MovePatternType bulletMove,
                                EnemyPlaneType type) {
        super(new EnemyPlane(x,y), new ImageView("resources/enemy_plane_white_2.png"));
        movePattern = MovePattern.newMovePattern(planeMove);
        bulletPatternType = bulletMove;
        enemyPlaneType = type;
        checkDefault();
    }

    public boolean deathEffect() {
        if (deathEffect) return false;
        deathEffect = true;
        GameObject go = gameObject;
        ExplosionSingleControllerManager.instance.add
                (new ExplosionController(go.getX(),go.getY(),go.getSizeX(),go.getSizeY()));
        return true;
    }


    public boolean deleteNow() {
        return gameObject.deleteNow() && bulletController.deleteNow();
    }

    //**********  GETTER/SETTER *****************************************************************
    public void setBulletImage(String link) {
        bulletImage = Utils.getImage(link);
    }

    public void setBulletImage(Image im) {
        bulletImage = im;
    }

    public boolean isBoss() {return isBoss;}


    //**********  COLLISION ******************************************************************
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof PlaneController) {
            GameObject plane = col.getCollisionObject();
            gameObject.takeDamage(plane.getDamage()*2);
        }
        if (col instanceof BulletController) {
            GameObject bullet = col.getCollisionObject();
            gameObject.takeDamage(bullet.getDamage());
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

        if (gameObject.getSizeX()==80) bulletPatternType = MovePatternType.FOLLOW;

        EnemyBulletController bc = new EnemyBulletController(go.getCornerX() + go.getSizeX()/2,
                                                   go.getCornerY() + go.getSizeY(), bulletPatternType);

        if (gameObject.getSizeX()==80) {bc.setSizeX(40); bc.setSizeY(40);}

        bc.setImage(bulletImage);
        bulletController.add(bc);
    }

    public void draw(Graphics g) {
        if (!gameObject.getDead()) gameView.drawImage(g,gameObject);
        bulletController.draw(g);
    }

    public void run() {
        bulletController.run();
        if (gameObject.getDead()) deathEffect();
        if (gameObject.getDead()) return;

        gameVector.x = 0;
        gameVector.y = gameObject.getMoveSpeed();
        movePattern.move(gameObject);
        gameObject.move(gameVector);
        attack();
    }
}
