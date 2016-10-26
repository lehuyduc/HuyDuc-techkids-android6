package controllers;

import main.GameConfig;
import main.GamePlay;
import models.Explosion;
import models.GameObject;
import views.AnimationView;
import views.GameView;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class ExplosionController extends SingleController {


    public ExplosionController(GameObject go, GameView gv) {
        super(go, gv);
    }

    public ExplosionController(int x,int y,int sx,int sy) {
        super(new Explosion(x,y,sx,sy),
                new AnimationView("explosion.png",6,32,32, GameConfig.explosionDelay));
    }

    @Override
    public void draw(Graphics g) {
        gameView.drawImage(g,gameObject);
    }

    @Override
    public void run() {

    }
}
