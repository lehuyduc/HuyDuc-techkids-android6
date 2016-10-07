import java.util.Random;

/**
 * Created by Le Huy Duc on 06/10/2016.
 */
public class GameLevel {
    private static int BACKGROUND_WIDTH = Object.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = Object.BACKGROUND_HEIGHT;
    private static Random rd = new Random();

    private static int MAX_WAVE = 2;
    public static int[] count = new int[10];
    private static boolean colored = false;

    public static void multipleSpam(int num) {
        for (int i=0;i<num;i++) {
            int n = ++Object.countBot;
            Object.Bots[n-1] = new Bot(rd.nextInt(BACKGROUND_WIDTH)+1,0);
            if (colored) {
                Object.Bots[n-1].setPhoto("resources/enemy_plane_yellow_1.png");
                Object.Bots[n-1].setHealth(200);
            }

        }
    }

    public static void Wave0() {
        if (count[0]==3) return;
        count[0]++;
        multipleSpam(count[0]*3);
    }

    public static void Wave1() {
        if (count[1]==3) return;
        count[1]++;
        for (int i=1;i<=2;i++) multipleSpam(count[1]*3);
    }

    public static void nextWave() {
        for (int i=0;i<MAX_WAVE;i++) if (count[i] < 3 && Object.countBot==0) {
            if (i==0) Wave0();
            if (i==1) {colored = true; Wave1();}
        }
    }
}
