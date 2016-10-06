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
