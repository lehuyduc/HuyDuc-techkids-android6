package controllers.Enemy;

import controllers.*;
import controllers.Enemy.Movement.*;
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

    private ControllerManager bulletController;
    private MovePattern movePattern;
    private MovePatternType bulletPatternType;
    private Image bulletImage = null;


    //**********  CONSTRUCTOR/DESTRUCTOR ******************************************************************
    void checkDefault() {
        if (bulletController==null) bulletController = new ControllerManager();
        if (movePattern==null) movePattern = new MovePatternDown();
        if (bulletPatternType==null) bulletPatternType = MovePatternType.DOWN;
        if (gameObject.getMoveSpeed()==0) gameObject.setMoveSpeed(1);
        if (gameObject.getHealth()==1) gameObject.setHealth(100);
        if (gameObject.yellow) gameObject.setHealth(200);
        if (gameObject.getAttackSpeed()==0) gameObject.setAttackSpeed(2000);
        if (bulletImage == null) bulletImage = Utils.getImage("resources/enemy_bullet.png");
        CollisionManager.instance.add(this);
    }

    public EnemyPlaneController(GameObject go, GameView gv) {
        super(go, gv);
        checkDefault();
    }

    public EnemyPlaneController(int x,int y) {
        super(new EnemyPlane(x,y), new ImageView("resources/enemy_plane_white_2.png"));
        checkDefault();
    }

    public EnemyPlaneController(int x,int y,int sx,int sy) {
        super(new EnemyPlane(x,y,sx,sy), new ImageView("resources/enemy_plane_white_2.png"));
        checkDefault();
    }

    public EnemyPlaneController(int x,int y,MovePatternType tp) {
        super(new EnemyPlane(x,y), new ImageView("resources/enemy_plane_white_2.png"));
        movePattern = MovePattern.newMovePattern(tp);
        checkDefault();
    }

    public EnemyPlaneController(int x,int y,MovePatternType plane,MovePatternType bullet) {
        super(new EnemyPlane(x,y), new ImageView("resources/enemy_plane_white_2.png"));
        movePattern = MovePattern.newMovePattern(plane);
        bulletPatternType = bullet;
        checkDefault();
    }

    public EnemyPlaneController(int x,int y,MovePatternType plane, MovePatternType bullet, boolean yellow) {
        super(new EnemyPlane(x,y), new ImageView("resources/enemy_plane_white_2.png"));
        if (yellow) {gameView.setImage("resources/enemy_plane_yellow_2.png"); gameObject.yellow = true;}
        movePattern = MovePattern.newMovePattern(plane);
        bulletPatternType = bullet;
        checkDefault();
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

    //**********  GETTER/SETTER *****************************************************************
    public void setBulletImage(String link) {
        bulletImage = Utils.getImage(link);
    }

    public void setBulletImage(Image im) {
        bulletImage = im;
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
