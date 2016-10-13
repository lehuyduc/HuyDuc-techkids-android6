package views;

import models.GameObject;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class ImageView implements GameView {
    protected Image image;

    public void setImage(String link) {
        image = Utils.getImage(link);
    }

    public void setImage(Image photo) {
        this.image = photo;
    }

    public synchronized void drawImage(Graphics g, GameObject go) {
        g.drawImage(image,go.getCornerX(),go.getCornerY(),go.getSizeX(),go.getSizeY(),null);
    }

    public ImageView(Image photo) {
        this.image = photo;
    }

    public ImageView(String link) {
        this.image = Utils.getImage(link);
    }

    public synchronized void run() {

    }
}
