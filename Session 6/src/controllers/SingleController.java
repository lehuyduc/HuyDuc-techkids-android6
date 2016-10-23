package controllers;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import main.GamePlay;
import models.GameObject;
import models.GameVector;
import views.GameView;
import views.ImageView;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class SingleController implements BaseController {
    public static final int BACKGROUND_WIDTH = GamePlay.BACKGROUND_WIDTH,
                            BACKGROUND_HEIGHT = GamePlay.BACKGROUND_HEIGHT;

    protected GameObject gameObject;
    protected GameView gameView;
    protected GameVector gameVector;

    protected boolean deathEffect = false;
    protected boolean canCollide = true;

    //**********  GETTER / SETTER******************************************************************
    public GameObject getGameObject() {return gameObject;}

    public GameView getGameView() {return gameView;}

    public GameVector getGameVector() {return gameVector;}

    public void setGameVector(int x,int y) {gameVector.x = x; gameVector.y = y;}

    public void setImage(String link) {
        if (gameView instanceof ImageView) gameView.setImage(link);
    }

    public void setImage(Image im) {
        if (gameView instanceof ImageView) gameView.setImage(im);
    }


    //**********  GETTER / SETTER FOR GAME OBJECT************************************************************
    //**********  SET OPERATOR  ***************************************************************
    public void setX(int v) {gameObject.setX(v);}

    public void setY(int v) {gameObject.setY(v);}

    public void setSizeX(int v) {gameObject.setSizeX(v);}

    public void setSizeY(int v) {gameObject.setSizeY(v);}

    public void setMoveSpeed(int v) {gameObject.setMoveSpeed(v);}

    public void setAttackSpeed(int v) {gameObject.setAttackSpeed(v);}

    public void setDamage(int v) {gameObject.setDamage(v);}

    public void setHealth(int v) {
        gameObject.setHealth(v);
    }

    public void takeDamage(int v) {gameObject.takeDamage(v);}

    public void setDead(boolean v) {gameObject.setDead(v);}

    public void setCanCollide(boolean v) {canCollide = v;}
    

    //**********  GET OPERATOR  ***************************************************************
    public int getX() {return gameObject.getX();}

    public int getY() {return gameObject.getY();}

    public int getCornerX() {return gameObject.getCornerX();}

    public int getCornerY() {return gameObject.getCornerY();}

    public int getSizeX() {return gameObject.getSizeX();}

    public int getSizeY() {return gameObject.getSizeY();}

    public int getMoveSpeed() {return gameObject.getMoveSpeed();}

    public int getAttackSpeed() {return gameObject.getAttackSpeed();}

    public int getDamage() {return gameObject.getDamage();}

    public int getHealth() {return gameObject.getHealth();}

    public boolean getDead() {return gameObject.getDead();}

    public boolean getCanCollide() {return canCollide;}



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

    public SingleController() {

    }

    public SingleController(GameObject go, GameView gv) {
        this.gameObject = go;
        this.gameView = gv;
        gameVector = new GameVector();
    }

    public boolean deleteNow() {
        return gameObject.deleteNow();
    }
}
