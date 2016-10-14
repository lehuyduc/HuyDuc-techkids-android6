package controllers;

import controllers.Enemy.EnemyBulletController;
import controllers.Enemy.EnemyPlaneController;
import controllers.Enemy.EnemyPlaneControllerManager;
import main.GameWindow;
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

    private KeyInput keyInput = new KeyInput();
    public KeyInputListener kil = new KeyInputListener(keyInput);
    private MouseInput mouseInput = new MouseInput();
    public MouseInputListener mil = new MouseInputListener(mouseInput);
    private boolean useMouse = false;

    private ControllerManager bulletManager = new ControllerManager();
    private int live = 5;
    private boolean deathEffect = false, godMode = false;
    private long lastDead = 0;


    //**********  CONSTRUCTOR ******************************************************************
    public void checkDefault() {
        gameObject.setSizeX(50);
        gameObject.setSizeY(50);
        gameObject.setMoveSpeed(10);
        gameObject.setAttackSpeed(300);
        if (useMouse) {gameObject.setDamage(45); gameObject.setHealth(1);}
        else {gameObject.setDamage(55); gameObject.setHealth(1);}
    }

    public PlaneController(GameObject go, GameView gv) {
        super(go,gv);
        CollisionManager.instance.add(this);
    }

    public PlaneController(int x,int y,boolean mouse) {
        super(new Plane(x,y), new ImageView("resources/plane2.png"));
        if (mouse) gameView.setImage("resources/plane3.png");
        useMouse = mouse;
        checkDefault();
        CollisionManager.instance.add(this);
    }


    //**********  COLLISION ******************************************************************
    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public boolean getCanCollide() {
        return this.canCollide;
    }

    @Override
    public void setCanCollide(boolean v) {
        this.canCollide = v;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof EnemyPlaneController) {
            GameObject enemyPlane = col.getCollisionObject();
            gameObject.takeDamage(enemyPlane.getHealth());
        }
        if (col instanceof EnemyBulletController) {
            GameObject bullet = col.getCollisionObject();
            gameObject.takeDamage(bullet.getDamage());
        }
        if (col instanceof BombController) {
            GameObject bomb = col.getCollisionObject();
            EnemyPlaneControllerManager.instance.takeDamage(bomb.getDamage());
        }
    }

    //**********  PLANE MODEL + DRAW + CONTROLLER ******************************************************************
    private long lastAttack = 0;
    private void attack() {
        long now = System.currentTimeMillis();
        if (now - lastAttack < gameObject.getAttackSpeed()) return;
        lastAttack = now;
        BulletController bc = new BulletController(gameObject.getCornerX() + gameObject.getSizeX() / 2,
                gameObject.getCornerY(),gameObject.getDamage());
        bulletManager.add(bc);
    }

    private boolean deathEffect() {
        if (deathEffect) return false;
        deathEffect = true;
        godMode = true;
        this.setCanCollide(false);
        lastDead = System.currentTimeMillis();
        GameObject go = gameObject;
        ExplosionControllerManager.instance.add
                (new ExplosionController(go.getX(),go.getY(),go.getSizeX(),go.getSizeY()));
        return true;
    }

    private void respawn() {
        live--;
        if (useMouse) gameObject.setHealth(1);
        else gameObject.setHealth(1);
        deathEffect = false;
        gameObject.setDead(false);
        gameObject.setX(GameWindow.BACKGROUND_WIDTH/2);
        gameObject.setY(GameWindow.BACKGROUND_HEIGHT/3*5);
    }

    public void draw(Graphics g) {
        if (!gameObject.getDead()) gameView.drawImage(g,gameObject);
        bulletManager.draw(g);
    }

    public void run() {
        bulletManager.run();
        if (gameObject.getDead()) deathEffect();
        long now = System.currentTimeMillis();
        if (now - lastDead >= 1500 && gameObject.getDead() && live > 0) respawn();
        if (now - lastDead >= 2250 && godMode && !gameObject.getDead()) {this.setCanCollide(true); godMode = false;}

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
            gameObject.move(gameVector,true);
        }
        else {
            Point p = mouseInput.mouseLocation;
            gameObject.moveTo(p.x,p.y,true);
            if (mouseInput.pressed) attack();
        }
    }

    public static final PlaneController instance1 = new PlaneController(500,500,false);
    public static final PlaneController instance2 = new PlaneController(700,500,true);
}
