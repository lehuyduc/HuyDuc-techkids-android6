package controllers;

import models.GameObject;
import models.GameVector;
import views.GameView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class SingleController implements BaseController {
    protected GameObject gameObject;
    protected GameView gameView;
    protected GameVector gameVector;

    //**********  GETTER  ******************************************************************
    public GameObject getGameObject() {return gameObject;}

    public GameView getGameView() {return gameView;}

    public GameVector getGameVector() {return gameVector;}

    public void setGameVector(int x,int y) {gameVector.x = x; gameVector.y = y;}

    public void setImage(String link) {
        gameView.setImage(link);
    }

    //**********  VIEW FUNCTIONS  ******************************************************************
    public void draw(Graphics g) {
        if (!gameObject.getDead()) gameView.drawImage(g,gameObject);
    }

    //**********  CONTROLLER FUNCTIONS  ******************************************************************

    public void run() {
        if (!gameObject.getDead()) {
            gameObject.move(gameVector);
            gameView.run();
        }
    }

    public SingleController(GameObject go, GameView gv) {
        this.gameObject = go;
        this.gameView = gv;
        gameVector = new GameVector();
    }

    public boolean needDelete() {
        return gameObject.needDelete();
    }
}
