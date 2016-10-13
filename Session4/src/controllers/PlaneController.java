package controllers;

import models.GameObject;
import models.Plane;
import utilities.KeyInput;
import utilities.KeyInputListener;
import utilities.MouseInput;
import utilities.MouseInputListener;
import views.GameView;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class PlaneController extends SingleController implements Colliable{

    private ControllerManager bulletManager = new ControllerManager();
    private KeyInput keyInput = new KeyInput();
    public KeyInputListener kil = new KeyInputListener(keyInput);
    private MouseInput mouseInput = new MouseInput();
    public MouseInputListener mil = new MouseInputListener(mouseInput);

    private boolean useMouse = false;

    public PlaneController(GameObject go, GameView gv) {
        super(go,gv);
        CollisionPool.instance.add(this);
    }

    public PlaneController(int x,int y,boolean mouse) {
        super(new Plane(x,y), new ImageView("resources/plane2.png"));
        if (mouse) gameView.setImage("resources/plane3.png");
        useMouse = mouse;
        gameObject.setSizeX(50);
        gameObject.setSizeY(50);
        gameObject.setMoveSpeed(10);
        if (!mouse) gameObject.setDamage(50);
        else gameObject.setDamage(40);
        CollisionPool.instance.add(this);
    }


    //**********  COLLISION ******************************************************************
    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof EnemyPlaneController) {
            GameObject enemyPlane = col.getCollisionObject();
            gameObject.takeDamage(enemyPlane.getHealth());
        }
        if (col instanceof BulletController) {
            GameObject bullet = col.getCollisionObject();
            if (bullet.getEnemy()) gameObject.takeDamage(bullet.getDamage());
        }
    }

    //**********  PLANE MODEL + DRAW + CONTROLLER ******************************************************************
    private long lastAttack = 0;
    private void attack() {
        long now = System.currentTimeMillis();
        if (now - lastAttack < gameObject.getAttackSpeed()) return;
        lastAttack = now;
        BulletController bc = new BulletController(gameObject.getCornerX() + gameObject.getSizeX() / 2,
                gameObject.getCornerY(), false);
        bulletManager.add(bc);
    }

    public synchronized void draw(Graphics g) {
        if (!gameObject.getDead()) gameView.drawImage(g,gameObject);
        bulletManager.draw(g);
    }

    public synchronized void run() {
        bulletManager.run();

        if (gameObject.getDead()) return;
        gameVector.x = 0;
        gameVector.y = 0;

        if (!useMouse) {
            if (keyInput.keyLeft) gameVector.x -= gameObject.getMoveSpeed();
            if (keyInput.keyRight) gameVector.x += gameObject.getMoveSpeed();
            if (keyInput.keyLeft && keyInput.keyRight) gameVector.x = 0;
            if (keyInput.keyDown) gameVector.y += gameObject.getMoveSpeed();
            if (keyInput.keyUp) gameVector.y -= gameObject.getMoveSpeed();
            if (keyInput.keyUp && keyInput.keyDown) gameVector.y =  0;
            if (keyInput.keySpace)  attack();

            gameObject.move(gameVector);
        }
        else {
            Point p = mouseInput.mouseLocation;
            gameObject.moveTo(p.x,p.y);
            if (mouseInput.pressed) attack();
        }
    }

}
