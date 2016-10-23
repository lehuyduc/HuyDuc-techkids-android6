package views;

import main.GameConfig;
import models.GameObject;
import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class ImageView implements GameView{
    protected Image image;

    public void setImage(String link) {
        image = Utils.getImage(link);
    }

    public void setImage(Image im) {
        this.image = im;
    }

    public Image getImage() {return image;}

    public ImageView(Image photo) {
        this.image = photo;
    }

    public ImageView(String link) {
        this.image = Utils.getImage(link);
    }

    public void drawImage(Graphics g, GameObject go) {
        int sql = GameConfig.SQUARE_LENGTH;
        int x = go.getColumn() * sql + sql/2;
        int y = go.getRow() * sql + sql/2;
        int cornerX = x - go.getWidth()/2;
        int cornerY = y - go.getHeight()/2;
        g.drawImage(image,cornerX,cornerY,go.getWidth(),go.getHeight(),null);
    }

    public void run() {

    }
}