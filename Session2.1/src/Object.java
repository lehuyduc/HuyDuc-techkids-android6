import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Le Huy Duc on 05/10/2016.
 */
public class Object {

    public static final int BACKGROUND_WIDTH = 1000, BACKGROUND_HEIGHT = 600;
    public static Image BACKGROUND_IMAGE = null;

    public static Bullet[] Bullets = new Bullet[Bullet.MAX_BULLET];
    public static int countBullet = 0;

    public static Bot[] Bots = new Bot[Bot.MAX_BOT];
    public static int countBot = 0;

    public static boolean inside(Point x, Point tl, Point br) {
        return (x.x > tl.x && x.x < br.x && x.x > tl.y && x.x < br.y);
    }

    public static boolean collide(Point x1,int X1,int Y1,Point x2,int X2,int Y2) {
        Point[] arr = new Point[4];
        arr[0] = x1;
        arr[1] = new Point(x1.x+X1,x1.y);
        arr[2] = new Point(x1.x,x1.y+Y1);
        arr[3] = new Point(x1.x+X1,x1.y+Y1);

        Point tl = x2, br = new Point(x2.x+X2, x2.y+Y2);
        for (int i=0;i<4;i++) if (inside(arr[i],tl,br)) return true;
        return false;
    }


    public static void initAll() {
        try {
            BACKGROUND_IMAGE = ImageIO.read(new File("resources/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAll() {
        for (int i=0;i<countBullet;i++) if (Bullets[i].destruct()) {
            Bullets[i].copy(Bullets[countBullet-1]);
            countBullet--;
            i--;
        } else Bullets[i].move();


    }


}
