package models;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GiftBox extends GameObject {

    public static final int SIZEX = 35, SIZEY = 35;

    public GiftBox(int x,int y) {
        super(x,y,SIZEX,SIZEY);
    }

    public boolean deleteNow() {
        return oos() || getDead();
    }


}
