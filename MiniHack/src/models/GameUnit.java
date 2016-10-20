package models;

import main.GameConfig;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GameUnit extends GameObject {

    public static final int WIDTH = 50, HEIGHT = 50;

    public void setRow(int v) {
        int sql = GameConfig.SQUARE_LENGTH;
        row = v;
        setX(column * sql + sql/2);
    }

    public void setColumn(int v) {
        int sql = GameConfig.SQUARE_LENGTH;
        row = v;
        setY(column * sql + sql/2);
    }

    public GameUnit(int x,int y) {
        super(x,y,WIDTH,HEIGHT);
    }

    public GameUnit(int x,int y,int width,int height) {
        super(x,y,width,height);
    }
}
