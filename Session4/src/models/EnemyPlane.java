package models;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class EnemyPlane extends GameObject {

    public static int SIZEX = 50, SIZEY = 50;

    public EnemyPlane(int x,int y) {
        super(x,y);
        setSizeX(SIZEX); setSizeY(SIZEY);
        setHealth(100);
    }

    public EnemyPlane(int x,int y,int sx,int sy) {
        super(x,y,sx,sy);
        setHealth(100);
    }

    public boolean needDelete() {
        return oos() || dead;
    }
}
