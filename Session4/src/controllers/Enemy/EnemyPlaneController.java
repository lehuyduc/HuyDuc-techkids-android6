package controllers.Enemy;

import controllers.*;
import controllers.Enemy.Movement.*;
import models.EnemyPlane;
import models.GameObject;
import views.GameView;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class EnemyPlaneController extends SingleController implements Colliable {

    private ControllerManager bulletController;
    private MovePattern movePattern;


    //**********  CONSTRUCTOR/DESTRUCTOR ******************************************************************
    void checkDefault() {
        if (bulletController==null) bulletController = new ControllerManager();
        if (movePattern==null) movePattern = new MovePatternDown();
        if (gameObject.getMoveSpeed()==0) gameObject.setMoveSpeed(1);
        if (gameObject.getHealth()==1) gameObject.setHealth(100);
        if (gameObject.getAttackSpeed()==0) gameObject.setAttackSpeed(3000);
    }

    public EnemyPlaneController(GameObject go, GameView gv) {
        super(go, gv);
        CollisionManager.instance.add(this);
    }

    public EnemyPlaneController(int x,int y) {
        super(new EnemyPlane(x,y), new ImageView("resources/enemy_plane_white_2.png"));
        checkDefault();
        CollisionManager.instance.add(this);
    }

    public EnemyPlaneController(int x,int y,int sx,int sy) {
        super(new EnemyPlane(x,y,sx,sy), new ImageView("resources/enemy_plane_white_2.png"));
        checkDefault();
        CollisionManager.instance.add(this);
    }

    public EnemyPlaneController(int x,int y,MovePatternType tp) {
        super(new EnemyPlane(x,y), new ImageView("resources/enemy_plane_white_2.png"));
        if (tp==MovePatternType.ZIGZAG) movePattern = new MovePatternZigzag();
        if (tp==MovePatternType.RANDOM) movePattern = new MovePatternRandom();
        checkDefault();
        CollisionManager.instance.add(this);
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
        return gameObject.deleteNow() && bulletController.deleteNow();
    }

    //**********  COLLISION ******************************************************************
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public boolean getCanCollide() {
        return this.canCollide;
    }

    @Override
    public void setCanCollide(boolean v) {
        this.canCollide = false;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof PlaneController) {
            GameObject plane = col.getCollisionObject();
            gameObject.takeDamage(plane.getDamage()*2);
        }
        if (col instanceof BulletController) {
            GameObject bullet = col.getCollisionObject();
            if (!bullet.getEnemy()) gameObject.takeDamage(bullet.getDamage());
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
        BulletController bc = new BulletController(go.getCornerX() + go.getSizeX()/2,
                                                   go.getCornerY() + go.getSizeY(), true);
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
