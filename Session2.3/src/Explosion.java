import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Le Huy Duc on 07/10/2016.
 */
public class Explosion {
    static final int width = 32;
    static final int height = 32;
    static final int column = 6;
    static BufferedImage[] sprites = new BufferedImage[column];
    static BufferedImage bigImg = null;


    public static void initExplosion() {
        try {
            bigImg = ImageIO.read(new File("resources/explosion.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=0;i<6;i++) {
            int start = i*width + i + 1;
            sprites[i] = bigImg.getSubimage(start,1,width,height);
        }
    }

}
