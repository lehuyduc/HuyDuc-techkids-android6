package utilities;

import main.GamePlay;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class Utils {

    public static Image getImage(String link) {
        Image res = null;
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

    public static boolean insideMap(int x,int y) {
        int x0 = GamePlay.MAP_LEFT, y0 = GamePlay.MAP_TOP;
        int n = GamePlay.MAP_SIZE;
        return (x>=x0 && y>=y0 && x<x0+n && y<y0+n);
    }

    public static boolean hasWall(int x1,int y1,int x2,int y2) {
        if (!insideMap(x2,y2)) return false;
        if (x1==x2) {
            if (y1<y2) return GamePlay.wallDown[x1][y1];
            else return GamePlay.wallDown[x2][y2];
        }
        if (y1==y2) {
            if (x1<x2) return GamePlay.wallRight[x1][y1];
            else return GamePlay.wallRight[x2][y2];
        }
        return true;
    }

    public static boolean canMoveTo(int x1,int y1,int x2,int y2) {
        return !hasWall(x1,y1,x2,y2) && insideMap(x2,y2);
    }

}