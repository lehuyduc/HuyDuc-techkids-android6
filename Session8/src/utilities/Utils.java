package utilities;

import models.GameObject;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class Utils {

    public static BufferedImage getImage(String link) {
        BufferedImage res = null;
        try {
            res = ImageIO.read(new File("resources/" + link));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Scanner getScanner(String link) {
        Scanner res = null;
        try {
            res = new Scanner(new File(link));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static double sqr(int a) {
        return a * a;
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt(sqr(a.x - b.x) + sqr(a.y - b.y));
    }

    public static double distance(GameObject a, GameObject b) {
        return distance(new Point(a.getX(),a.getY()), new Point(b.getX(),b.getY()));
    }

    public static boolean inside(Point x, Point tl, Point br) {
        return (x.x > tl.x && x.x < br.x && x.y > tl.y && x.y < br.y);
    }

    public static boolean Collide(Point x1, int X1, int Y1, Point x2, int X2, int Y2) {
        Point[] arr = new Point[4];
        arr[0] = x1;
        arr[1] = new Point(x1.x + X1, x1.y);
        arr[2] = new Point(x1.x, x1.y + Y1);
        arr[3] = new Point(x1.x + X1, x1.y + Y1);

        Point tl = x2, br = new Point(x2.x + X2, x2.y + Y2);
        for (int i = 0; i < 4; i++) if (inside(arr[i], tl, br)) return true;
        return false;
    }

    public static boolean collide(Point x1, int X1, int Y1, Point x2, int X2, int Y2) {
        return Collide(x1, X1, Y1, x2, X2, Y2) || Collide(x2, X2, Y2, x1, X1, Y1);
    }

    public static Vector<Image> loadSprite(String link, int nImage, int width, int height,
                                           int offSetX, int offSetY) {
        Vector<Image> res = new Vector<>();
        BufferedImage sheetImage = null;
        try {
            sheetImage = ImageIO.read(new File("resources/"+link));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < nImage; i++) {
            int x = i * width + i + offSetX;
            int y = offSetY;
            Image image = sheetImage.getSubimage(x,y,width,height);
            res.add(image);
        }
        return res;
    }

    public static void playSound(String audioUrl, boolean repeat) {

        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            if(repeat) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                clip.loop(0);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}