package main.levels;

import main.GameConfig;
import main.GamePlay;

import java.awt.*;

/**
 * Created by Le Huy Duc on 20/10/2016.
 */
public class level1 {
    private int MAP_SIZE = 6;
    private int MAP_TOP = 0, MAP_LEFT = 0;
    private Point[] wallDownLoaction = new Point[50];
    private boolean[][] wallDown = GamePlay.wallDown;
    private Point[] wallRightLocation = new Point[50];
    private boolean[][] wallRight = GamePlay.wallRight;

    public void createLevel() {
        GamePlay.MAP_SIZE = this.MAP_SIZE;

        int n = 3;
        wallDownLoaction[0] = new Point(2,3);
        wallDownLoaction[1] = new Point(3,4);
        wallDownLoaction[2] = new Point(1,2);
        for (int i=0;i<n;i++) {
            int x = wallDownLoaction[i].x + MAP_LEFT;
            int y = wallDownLoaction[i].y + MAP_TOP;
            wallDown[x][y] = true;
        }

        // tương tự với wallRight
    }

    public static level1 instance = new level1();
}
