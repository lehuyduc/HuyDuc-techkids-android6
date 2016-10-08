import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Le Huy Duc on 07/10/2016.
 */
public class Explosion {
    //**********  INIT EXPLOSION  ***************************************************************
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;
    public static final int PHASES = 6;
    private static BufferedImage[] sprites = new BufferedImage[PHASES+1];
    private static BufferedImage bigImg = null;
    public static final int MAX_EXPLOSION = 100;

    //********** IMAGE AND DRAWING  ***************************************************************
    private int x = 300, y = 0, cornerX = 0, cornerY = 0;
    private int sizeX = 50, sizeY = 50;
    private int horizon = 0;
    private Image photo;
    private boolean dead = false;
    private static int delay = 100;
    private long firstExplosion = System.currentTimeMillis();


    //**********  GAMEPLAY  ***************************************************************
    private int moveSpeed = 1;


    //**********  GAMEPLAY  ***************************************************************
    public void copy(Explosion other) {
        x = other.x; y = other.y;
        cornerX = other.cornerX; cornerY = other.cornerY;
        sizeX = other.sizeX; sizeY = other.sizeY;
        horizon = other.horizon;
        photo = other.photo;
        dead = other.dead;
        firstExplosion = other.firstExplosion;
        moveSpeed = other.moveSpeed;
    }


    //********** DRAWING FUNCTIONS  ***************************************************************
    public void drawImage(Graphics g) {g.drawImage(photo,cornerX,cornerY,sizeX,sizeY,null);}

    public int getPhase() {
        long now = System.currentTimeMillis();
        for (int i=PHASES;i>=0;i--) if (now-firstExplosion > i*delay) {
            photo = sprites[i];
            return i;
        }
        return 0;
    }




    //**********  GETTER  ***************************************************************
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCornerX() {
        return cornerX;
    }

    public int getCornerY() {
        return cornerY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getHorizon() {
        return horizon;
    }

    public boolean getDead() {
        return dead;
    }

    public Image getPhoto() {
        return photo;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }


    //**********  SETTER  ***************************************************************
    public void setX(int x) { this.x = x;this.cornerX = x - sizeX/2;}

    public void setY(int y) {this.y = y;this.cornerY = y - sizeY/2;}

    public void setCornerX(int cornerX) {
        this.cornerX = cornerX;
    }

    public void setCornerY(int cornerY) {
        this.cornerY = cornerY;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public void setHorizon(int horizon) {
        this.horizon = horizon;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    //**********  INIT EXPLOSION  ***************************************************************
    public static void initExplosion() {
        try {
            bigImg = ImageIO.read(new File("resources/explosion.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=0;i<PHASES;i++) {
            int start = i*WIDTH + i + 1;
            sprites[i] = bigImg.getSubimage(start,1,WIDTH,HEIGHT);
        }
        sprites[PHASES] = null;
    }

    //**********  DESTRUCT  ***************************************************************
    public boolean destruct() {
        if (!dead) return false;
        return true;
    }

    //**********  CONSTRUCTOR ***************************************************************

    public Explosion(int x,int y) {
        setX(x); setY(y);
        firstExplosion = System.currentTimeMillis();
    }

    public Explosion(int x,int y,int sx,int sy) {
        setX(x); setY(y);
        setSizeX(sx);
        setSizeY(sy);
    }
}
