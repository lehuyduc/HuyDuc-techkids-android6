package views;

import java.awt.*;

import models.Plane;
import utilities.*;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class PlaneView {
    private Image image = null;

    public Image getImage() {return image;}

    public void setImage(String link) {
        image = Utils.getImage(link);
    }

    public void setImage(Image photo) {
        image = photo;
    }

    public void drawImage(Graphics g, Plane plane) {
        g.drawImage(image,plane.getCornerX(),plane.getCornerY(),plane.getSizeX(),plane.getSizeY(),null);
    }

    public PlaneView(Image photo) {
        image = photo;
    }
}
