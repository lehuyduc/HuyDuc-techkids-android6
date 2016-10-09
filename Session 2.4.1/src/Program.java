import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

/**
 * Created by Le Huy Duc on 02/10/2016.
 */
public class Program extends JFrame {
    public static void main(String[] args) {
        //System.out.println("Hello world")

        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();
    }

}
