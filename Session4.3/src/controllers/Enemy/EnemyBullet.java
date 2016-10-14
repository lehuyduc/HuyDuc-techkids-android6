package controllers.Enemy;

import models.GameObject;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class EnemyBullet extends GameObject {

    private static int SIZEX = 32, SIZEY = 32;

    public EnemyBullet(int x,int y) {
        super(x,y,SIZEX,SIZEY);
    }

    public EnemyBullet(int x,int y,int sx,int sy) {
        super(x,y,sx,sy);
    }

    public boolean deleteNow() {
        return oos() || dead;
    }
}
