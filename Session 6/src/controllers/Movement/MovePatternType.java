package controllers.Movement;

import java.util.Random;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public enum MovePatternType {
    DOWN,
    AIM,
    FOLLOW,
    RANDOM,
    ZIGZAG;
    public static int countFollow = 0;

    public static MovePatternType getRandom(boolean useAll) {
        Random rd = new Random();
        int tp = rd.nextInt(3);
        if (tp==0) return DOWN;
        if (!useAll) return DOWN;
        if (tp==1) return AIM;
        if (tp==2 && countFollow>0) {countFollow--; return FOLLOW;}
        return DOWN;
    }
}
