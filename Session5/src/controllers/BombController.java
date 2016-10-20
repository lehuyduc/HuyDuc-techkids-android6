package controllers;

import main.GamePlay;
import models.Bomb;
import models.GameObject;
import models.Plane;
import views.ImageView;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class BombController extends SingleController implements Colliable {

    public BombController(int x,int y) {
        super(new Bomb(x,y), new ImageView("resources/bomb.png"));
        gameObject.setDamage(350);
        CollisionManager.instance.add(this);
    }

    @Override
    public GameObject getCollisionObject() {
        return gameObject;
    }

    @Override
    public void onCollide(Colliable col) {
        if (col instanceof PlaneController) {
            gameObject.takeDamage(1000);
            GamePlay.lastBomb = System.currentTimeMillis();
        }
    }
}
