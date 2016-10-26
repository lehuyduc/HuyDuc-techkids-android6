package views;

import main.GameConfig;
import main.GamePlay;
import main.GameWindow;
import models.GameObject;
import utilities.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class AnimationView implements GameView{

    private long animationDelay = GameConfig.DEFAULT_ANIMATION_DELAY;
    private long lastDraw = 0;
//    static BufferedImage bigImg = null;
//    static Image[] sprite = new Image[10];
//    static int phases = 6;
//    static boolean inited = false;
    int nImage;
    private Vector<Image> imageVector;
    private int index = 0;

    public AnimationView(String link,int nImage,int width,int height,long animationDelay) {
        imageVector = Utils.loadSprite(link,nImage,width,height,1,1);
        this.nImage = nImage;
        this.animationDelay = animationDelay;
    }

    public void setImage(String link) {

    }

    public void setImage(Image im) {

    }

    public Image getImage() {
        return null;
    }

    public void drawImage(Graphics g, GameObject go) {
        g.drawImage(imageVector.get(index),go.getCornerX(),go.getCornerY(),go.getSizeX(),go.getSizeY(),null);
        long now = System.currentTimeMillis();
        if (now - lastDraw < animationDelay) return;
        lastDraw = now;
        index++;
        if (index>=nImage) index = 0;
    }

    public void run() {

    }
}
