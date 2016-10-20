package script;

/**
 * Created by Le Huy Duc on 17/10/2016.
 */
public enum ObjectType {
    PLANE,
    BULLET,
    BOMB,
    ENEMY,
    ENEMY_BULLET,
    INVALID;

    public static ObjectType getObjectType(String s) {
        if (s.equals("PLANE")) return PLANE;
        if (s.equals("BULLET")) return BULLET;
        if (s.equals("BOMB")) return BOMB;
        if (s.equals("ENEMY")) return ENEMY;
        if (s.equals("ENEMY_BULLET")) return ENEMY_BULLET;
        return INVALID;
    }
}
