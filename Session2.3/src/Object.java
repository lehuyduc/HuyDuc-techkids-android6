import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Le Huy Duc on 05/10/2016.
 */
// All interactions between objects happen here
public class Object {

    public static final int BACKGROUND_WIDTH = 1000, BACKGROUND_HEIGHT = 600;
    public static Image BACKGROUND_IMAGE = null, EXPLOSION = null;

    public static Image WHITE2 = null;
    public static Image WHITE3 = null;
    private static boolean firstTime = true;

    public static MyPlane[] myPlanes = new MyPlane[MyPlane.MAX_PLANE];
    public static int countMyPlane = 0;

    public static Bullet[] Bullets = new Bullet[Bullet.MAX_BULLET];
    public static int countBullet = 0;

    public static Bot[] Bots = new Bot[Bot.MAX_BOT];
    public static int countBot = 0;


    //**********  OBJECTS DELETE  ***************************************************************
    public static void deleteBullet(int i) {
        Bullets[i].copy(Bullets[countBullet-1]);
        countBullet--;
    }

    public static void deleteBot(int i) {
        Bots[i].copy(Bots[countBot-1]);
        countBot--;
    }

    //**********  INITIALIZING PLANES  ***************************************************************
    public static void initMyPlanes() {
        countMyPlane = 2;
        for (int i=0;i<countMyPlane;i++) myPlanes[i] = new MyPlane(0,0,null);

        myPlanes[0].setX(300);
        myPlanes[0].setY(BACKGROUND_HEIGHT-100);
        myPlanes[0].setMoveSpeed(10);
        myPlanes[0].setPhoto("resources/plane1.png");

        myPlanes[1].setX(700);
        myPlanes[1].setY(BACKGROUND_HEIGHT-100);
        myPlanes[1].setUseMouse(true);
        myPlanes[1].setMoveSpeed(3);
        myPlanes[1].setPhoto("resources/plane2.png");
    }


    //**********  GLOBAL INITIALIZATION  ***************************************************************
    public static void initAll() {
        BACKGROUND_IMAGE = Self.getImage("resources/background.png");

        if (firstTime) {
            countBot++;
            Bots[0] = new Bot();
            Bots[0].setX(400);
            Bots[0].setY(200);
            firstTime = false;
        }

        WHITE2 = Self.getImage("resources/enemy_plane_white_2.png");
        WHITE3 = Self.getImage("resources/enemy_plane_white_3.png");

        initMyPlanes();
    }


    //**********  GLOBAL UPDATE OBJECTS  ***************************************************************
    public static void updateAll() {

        //**********  OBJECTS CREATION  *************************************************************
        GameLevel.nextWave();
        long now = System.currentTimeMillis();

        for (int i=0;i<countMyPlane;i++) if (myPlanes[i].getDead() && now-myPlanes[i].lastDead>=2000) {
            myPlanes[i].respawn();
        } else
            if (myPlanes[i].getHealth() > 1000000 && now-myPlanes[i].lastDead>=3000) myPlanes[i].setHealth(1);

        //**********  OBJECTS INTERATION *************************************************************
        Bot t = null; Bullet b = null; MyPlane p = null;
        for (int i=0;i<countBot;i++) {
            t = Bots[i];

            // Player Bullet vs Bot
            for (int j=0;j<countBullet;j++) if (!Bullets[j].getDead()) {
                b = Bullets[j];
                if (Self.collide(new Point(t.getCornerX(),t.getCornerY()), t.getSizeX(), t.getSizeY(),
                                 new Point(b.getCornerX(),b.getCornerY()), b.getSizeX(), b.getSizeY())) {
                    Bots[i].takeDamage(Bullets[j].getDamage());
                    Bullets[j].setDead(true);
                    break;
                }
            }

            // Bot vs Player
            for (int j=0;j<countMyPlane;j++) if (!myPlanes[j].getDead()) {
                p = myPlanes[j];
                if (Self.collide(new Point(t.getCornerX(),t.getCornerY()), t.getSizeX(), t.getSizeY(),
                                 new Point(p.getCornerX(),p.getCornerY()), p.getSizeX(), p.getSizeY())) {
                    myPlanes[j].takeDamage(Bots[i].getHealth());
                    Bots[i].takeDamage(100);
                }
            }
        }

        //**********  OBJECTS DELETION  *************************************************************
        for (int i=0;i<countBullet;i++) if (Bullets[i].destruct()) {
            deleteBullet(i);
            i--;
        } else Bullets[i].move();

        for (int i=0;i<countBot;i++) if (Bots[i].destruct()) {
            deleteBot(i);
            i--;
        } else Bots[i].move();

        for (int i=0;i<countMyPlane;i++) if (myPlanes[i].destruct()) {

        }
    }


}
