import java.util.Random;

/**
 * Created by Le Huy Duc on 06/10/2016.
 */
public class GameLevel {
    private static int BACKGROUND_WIDTH = Object.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = Object.BACKGROUND_HEIGHT;
    private static Random rd = new Random();

    private static int MAX_WAVE = 10;
    public static int[] count = new int[10];
    private static boolean colored = false;

    public static void multipleSpam(int num) {
        for (int i=0;i<num;i++) {
            int n = ++Object.countBot;
            Object.bots[n-1] = new Bot(rd.nextInt(BACKGROUND_WIDTH)+1,0);
            if (colored) {
                Object.bots[n-1].setPhoto("resources/enemy_plane_yellow_1.png");
                Object.bots[n-1].setHealth(150);
            }
        }
    }


    public static void Wave0() {
        if (count[0]>=3) return;
        count[0]++;
        multipleSpam(count[0]*4);
    }

    public static void Wave1() {
        if (count[1]>=3) return;
        count[1]++;
        multipleSpam(count[1]*3);
    }

    public static void Wave2() {
        if (count[2]>=1) return;
        count[2]++;

        int n = ++Object.countBot;
        Object.bots[n-1] = Object.boss1;
        Object.boss1.enter();
    }

    public static void nextWave() {
        for (int i=0;i<MAX_WAVE;i++) if (count[i] < 3 && Object.countBot==0) {
            if (i==0) {Wave0(); return;}
            if (i==1) {colored = true; Wave1(); return;}
            if (i==2) Wave2();
        }
    }
}