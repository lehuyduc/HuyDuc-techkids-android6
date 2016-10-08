import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Le Huy Duc on 05/10/2016.
 */
// All interactions between objects happen here
public class Object {

    public static final int BACKGROUND_WIDTH = 1200, BACKGROUND_HEIGHT = 700;
    public static Image BACKGROUND_IMAGE = null, EXPLOSION = null;

    public static Image WHITE2 = null;
    public static Image WHITE3 = null;
    private static boolean firstTime = true;

    public static MyPlane[] myPlanes = new MyPlane[MyPlane.MAX_PLANE];
    public static int countMyPlane = 0;

    public static Bullet[] bullets = new Bullet[Bullet.MAX_BULLET];
    public static int countBullet = 0;

    public static Bot[] bots = new Bot[Bot.MAX_BOT];
    public static int countBot = 0;

    public static Explosion[] explosions = new Explosion[Explosion.MAX_EXPLOSION];
    public static int countExplosion = 0;

    public static Boss1 boss1 = new Boss1();


    //**********  OBJECTS DELETE  ***************************************************************
    public static void deleteBullet(int i) {
        bullets[i].copy(bullets[countBullet-1]);
        countBullet--;
    }

    public static void deleteBot(int i) {
        bots[i].copy(bots[countBot-1]);
        countBot--;
    }

    public static void deleteExplosion(int i) {
        explosions[i].copy(explosions[countExplosion-1]);
        countExplosion--;
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
        WHITE2 = Self.getImage("resources/enemy_plane_white_2.png");
        WHITE3 = Self.getImage("resources/enemy_plane_white_3.png");

        initMyPlanes();
        Explosion.initExplosion();

        if (firstTime) {
            countBot++;
            bots[0] = new Bot();
            bots[0].setX(400);
            bots[0].setY(200);
            firstTime = false;
        }
    }

    //**********  GLOBAL UPDATE OBJECTS  ***************************************************************
    public static void updateAll() {

        //**********  OBJECTS UPDATE  *************************************************************
        GameLevel.nextWave();
        long now = System.currentTimeMillis();
        long lastExplode = now;

        for (int i=0;i<countMyPlane;i++) if (myPlanes[i].getDead() && now-myPlanes[i].lastDead>=2000) {
            myPlanes[i].respawn();
        } else
            if (myPlanes[i].getHealth() > 1000000 && now-myPlanes[i].lastDead>=3000) myPlanes[i].setHealth(1);

        for (int i=0;i<countExplosion;i++) {
            int j = explosions[i].getPhase();
            if (j==Explosion.PHASES) explosions[i].setDead(true);
        }


        //**********  OBJECTS INTERATION *************************************************************
        Bot t = null; Bullet b = null; MyPlane p = null;
        for (int i=0;i<countBot;i++) {
            t = bots[i];
            if (!t.isBoss) t.attack(50);

            // Player Bullet vs Bot
            for (int j=0;j<countBullet;j++) if (!bullets[j].getDead() && !bullets[j].getEnemy()) {
                b = bullets[j];
                if (t.isBoss) {
                    Point center = new Point(b.getX(), b.getY());
                    Point corner = new Point(b.getCornerX(), b.getCornerY());
                    if (Self.collide(Boss.toBoss(t), center, Self.distance(center,corner))) {
                        bots[i].takeDamage(bullets[j].getDamage());
                        bullets[j].takeDamage(100);
                    }
                }
                else
                if (Self.collide(new Point(t.getCornerX(),t.getCornerY()), t.getSizeX(), t.getSizeY(),
                                 new Point(b.getCornerX(),b.getCornerY()), b.getSizeX(), b.getSizeY())) {
                    bots[i].takeDamage(bullets[j].getDamage());
                    bullets[j].takeDamage(100);
                }
            }

            // Bot vs Player
            for (int j=0;j<countMyPlane;j++) if (!myPlanes[j].getDead()) {
                p = myPlanes[j];

                if (t.isBoss) {
                    Point center = new Point(p.getX(), p.getY());
                    Point corner = new Point(p.getCornerX(), p.getCornerY());

                    if (Self.collide(Boss.toBoss(t), center, Self.distance(center,corner))) {
                        myPlanes[j].takeDamage(bots[i].getHealth());
                        bullets[j].setDead(true);
                    }
                }
                else
                if (Self.collide(new Point(t.getCornerX(),t.getCornerY()), t.getSizeX(), t.getSizeY(),
                                 new Point(p.getCornerX(),p.getCornerY()), p.getSizeX(), p.getSizeY())) {
                    myPlanes[j].takeDamage(bots[i].getHealth());
                    bots[i].takeDamage(50);
                }
            }
        }

        //Player vs enemy bullet
        for (int i=0;i<countMyPlane;i++) if (!myPlanes[i].getDead()) {
            p = myPlanes[i];
            for (int j=0;j<countBullet;j++) if (bullets[j]!=null && !bullets[j].getDead() && bullets[j].getEnemy()) {
                b = bullets[j];
                if (Self.collide(new Point(p.getCornerX(), p.getCornerY()), p.getSizeX(), p.getSizeY(),
                        new Point(b.getCornerX(), b.getCornerY()), b.getSizeX(), b.getSizeY())) {
                    myPlanes[i].takeDamage(bullets[j].getDamage());
                    bullets[j].takeDamage(100);
                    break;
                }
            }
        }


        //**********  OBJECTS MOVEMENT AND DELETION  *************************************************************
        for (int i=0;i<countBullet;i++) if (bullets[i].destruct()) {
            deleteBullet(i);
            i--;
        } else bullets[i].move();

        for (int i=0;i<countBot;i++) if (bots[i].destruct()) {
            if (!bots[i].oos()) {
                Image imm = null;
                bots[i].setPhoto(imm);
                int n = ++countExplosion;
                explosions[n - 1] = new Explosion(bots[i].getX(), bots[i].getY(),bots[i].getSizeX(),bots[i].getSizeY());
            }
            deleteBot(i);
            i--;
        } else if (!bots[i].isBoss) bots[i].move();

        for (int i=0;i<countExplosion;i++) if (explosions[i].destruct()) {
            deleteExplosion(i);
            i--;
        }

        for (int i=0;i<countMyPlane;i++) if (myPlanes[i].destruct()) {
            int n = ++countExplosion;
            explosions[n-1] = new Explosion(myPlanes[i].getX(), myPlanes[i].getY());
        }


        //**********  BOSS CONTROL  *************************************************************
        if (!boss1.getDead()) boss1.action();
    }


}
