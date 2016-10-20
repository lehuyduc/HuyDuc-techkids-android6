package models;

import main.GameConfig;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GameObject {
    public static final int SQUARE_LENGTH = GameConfig.SQUARE_LENGTH;
    protected int x, y;
    protected int row, column;
    protected int width, height, cornerX, cornerY;

    public int getX() {return x;}

    public int getY() {return y;}

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int v) {x = v; cornerX = x - width/2;}

    public void setY(int v) {y = v; cornerY = y - height/2;}

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void move(int dx,int dy) {
        column += dx;
        row += dy;
    }


    public GameObject(int row, int column,int width,int height) {
        this.row = row;
        this.column = column;
        this.width = width;
        this.height = height;
    }
}
