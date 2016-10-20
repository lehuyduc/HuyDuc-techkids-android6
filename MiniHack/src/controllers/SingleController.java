package controllers;

import models.GameObject;
import views.GameView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class SingleController implements BaseController {

    protected GameObject gameObject;
    protected GameView gameView;

    public int getRow() {
        return gameObject.getRow();
    }

    public int getColumn() {
        return gameObject.getColumn();
    }

    public int getWidth() {
        return gameObject.getWidth();
    }

    public int getHeight() {
        return gameObject.getHeight();
    }

    public void setWidth(int width) {
        gameObject.setWidth(width);
    }

    public void setHeight(int height) {
        gameObject.setHeight(height);
    }

    public void setRow(int row) {
        gameObject.setRow(row);
    }

    public void setColumn(int column) {
        gameObject.setColumn(column);
    }



    public SingleController(GameObject go, GameView gv) {
        gameObject = go;
        gameView = gv;
    }

    public SingleController() {

    }

    public void move(GameObject go) {

    }

    public void draw(Graphics g) {
        gameView.drawImage(g,gameObject);
    }

    public void run() {

    }
}
