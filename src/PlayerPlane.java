import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Le Huy Duc on 03/10/2016.
 */
public class PlayerPlane extends Frame{
    // Image
    private int backgroundX = 600, backgroundY = 400;
    private int planeX = 0, planeY = 0;
    private int sizeX, sizeY;
    Image thisPlane;

    private int moveSpeed;
    private int lives;

    public boolean KeyMov;
    public boolean MouseMove;
}
