package views;

import models.EnemyPlane;
import utilities.Utils;
import control.Object;
import java.awt.*;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class EnemyPlaneView {
    private Image image = null;

    public Image getImage() {return image;}

    public void setImage(String link) {image = Utils.getImage(link);}

    public void setImage(Image photo) {image = photo;}

    public void drawImage(Graphics g, EnemyPlane ep) {
        g.drawImage(image,ep.getCornerX(),ep.getCornerY(),ep.getSizeX(),ep.getSizeY(),null);
    }

    public EnemyPlaneView(String link) {
        image = Utils.getImage(link);
    }

    public EnemyPlaneView(Image im) {
        image = im;
    }
}
