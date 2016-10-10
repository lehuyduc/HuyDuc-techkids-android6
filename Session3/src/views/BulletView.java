package views;

import models.Bullet;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class BulletView {
    private Image image = null;

    private Image getImage() {return image;}

    private void setImage(String link) {image = Utils.getImage(link);}

    public void drawImage(Graphics g, Bullet b) {
        g.drawImage(image,b.getCornerX(),b.getCornerY(),b.getSizeX(),b.getSizeY(),null);
    }

    public BulletView(Image photo) {
        image = photo;
    }
}
