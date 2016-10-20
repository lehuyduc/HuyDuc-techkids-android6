package main;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GamePlay {
    public static boolean playerTurn = true;
    public static final int DEFAULT_MAP_SIZE = GameConfig.DEFAULT_MAP_SIZE;
    public static int MAP_SIZE = GameConfig.DEFAULT_MAP_SIZE;
    public static int MAP_TOP = 0;
    public static int MAP_LEFT = 0;
    public static boolean[][] wallDown = new boolean[MAP_SIZE][MAP_SIZE];
    public static boolean[][] wallRight = new boolean[MAP_SIZE][MAP_SIZE];

    public static void initWall() {
        for (int i=0;i<DEFAULT_MAP_SIZE;i++)
            for (int j=0;j<DEFAULT_MAP_SIZE;j++) {

                if (wallDown[i][j]) {
                    // vẽ tường DƯỚI
                }

                if (wallRight[i][j]) {
                    // vẽ tường phải//
                }
            }
    }
}
