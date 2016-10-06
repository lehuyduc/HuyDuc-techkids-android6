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


    //**********  GLOBAL INITIALIZATION  ***************************************************************
    public static void initAll() {
        try {
            BACKGROUND_IMAGE = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        countBot++;
        Bots[0] = new Bot();
        Bots[0].setX(400);
        Bots[0].setY(200);
    }


    //**********  GLOBAL UPDATE OBJECTS  ***************************************************************
    public static void updateAll() {
        Bot t = null; Bullet b = null;
        for (int i=0;i<countBot;i++) {
            t = Bots[i];
            for (int j=0;j<countBullet;j++) {
                b = Bullets[j];
                if (Self.collide(new Point(t.getCornerX(),t.getCornerY()), t.getSizeX(), t.getSizeY(),
                                 new Point(b.getCornerX(),b.getCornerY()), b.getSizeX(), b.getSizeY())) {
                    Bots[i].takeDamage(Bullets[j].getDamage());
                    Bullets[j].setDead(true);
                    break;
                }
            }
        }

        for (int i=0;i<countBullet;i++) if (Bullets[i].destruct()) {
            deleteBullet(i);
            i--;
        } else Bullets[i].move();

        for (int i=0;i<countBot;i++) if (Bots[i].destruct()) {
            deleteBot(i);
            i--;
        } else Bots[i].move();

    }


}
