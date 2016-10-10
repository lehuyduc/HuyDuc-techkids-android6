package utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class Utils {

    public static Image getImage(String link) {
        Image res = null;
        try {
            res = ImageIO.read(new File(link));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
