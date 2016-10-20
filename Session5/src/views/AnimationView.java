package views;

import main.GamePlay;
import main.GameWindow;
import models.GameObject;
import utilities.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class AnimationView implements GameView{

    private long firstAnimation;
    static BufferedImage bigImg = null;
    static Image[] sprite = new Image[10];
    static int phases = 6;
    static boolean inited = false;

    public static void initExplosion() {
        try {
            bigImg = ImageIO.read(new File("resources/explosion.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i=0;i<phases;i++) {
            sprite[i] = bigImg.getSubimage(i*32+i+1,1,32,32);
        }
        sprite[phases] = null;
        inited = true;
    }

    public AnimationView() {
        if (!inited) initExplosion();
        firstAnimation = System.currentTimeMillis();
    }

    public void setImage(String link) {

    }

    public void setImage(Image im) {

    }

    public Image getImage() {
        return null;
    }

    public void drawImage(Graphics g, GameObject go) {
        long now = System.currentTimeMillis();
        int i = (int)(now - firstAnimation) / GamePlay.animationDelay;
        if (i>=phases) return;
        g.drawImage(sprite[i],go.getCornerX(),go.getCornerY(),go.getSizeX(),go.getSizeY(),null);
    }

    public void run() {

    }
}
