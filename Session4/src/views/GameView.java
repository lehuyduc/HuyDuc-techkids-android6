package views;

import models.GameObject;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class GameView {
    protected Image image;

    public void setImage(String link) {
        image = Utils.getImage(link);
    }

    public void setImage(Image photo) {
        this.image = photo;
    }

    public void drawImage(Graphics g, GameObject go) {
        g.drawImage(image,go.getCornerX(),go.getCornerY(),go.getSizeX(),go.getSizeY(),null);
    }

    public GameView(Image photo) {
        this.image = photo;
    }

    public GameView(String link) {
        this.image = Utils.getImage(link);
    }

    public void run() {

    }
}
