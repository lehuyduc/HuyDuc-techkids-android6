package controllers;

import models.Bullet;
import models.EnemyPlane;
import models.GameObject;
import views.GameView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class EnemyPlaneController extends SingleController implements Colliable {

    private ControllerManager bulletController = new ControllerManager();


    //**********  CONSTRUCTOR/DESTRUCTOR ******************************************************************
    public EnemyPlaneController(GameObject go, GameView gv) {
        super(go, gv);
        CollisionPool.instance.add(this);
    }

    public EnemyPlaneController(int x,int y) {
        super(new GameObject(x,y), new GameView("resources/enemy_plane_white_2.png"));
        gameObject.setSizeX(EnemyPlane.SIZEX);
        gameObject.setSizeY(EnemyPlane.SIZEY);
        gameObject.setMoveSpeed(1);
        gameObject.setHealth(100);
        gameObject.setAttackSpeed(1000);
        CollisionPool.instance.add(this);
    }

    public boolean needDelete() {
        return gameObject.needDelete() && bulletController.needDelete();
    }


    //**********  COLLISION ******************************************************************
   public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        System.out.println(col instanceof PlaneController);
        if (col instanceof PlaneController) {
            GameObject plane = col.getCollisionObject();
            gameObject.takeDamage(plane.getDamage()*2);
            System.out.println(plane.getDamage());
        }
        if (col instanceof BulletController) {
            GameObject bullet = col.getCollisionObject();
            if (!bullet.getEnemy()) gameObject.takeDamage(bullet.getDamage());
        }
    }

    //**********  MVC ******************************************************************
    private long lastAttack = 0;
    private void attack() {
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
        if (gameObject.getDead()) return;
        gameVector.x = 0;
        gameVector.y = gameObject.getMoveSpeed();
        gameObject.move(gameVector);
        attack();
    }
}
