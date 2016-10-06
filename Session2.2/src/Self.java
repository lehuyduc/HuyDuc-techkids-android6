import java.awt.*;

/**
 * Created by Le Huy Duc on 03/10/2016.
 */
public class Self {

    public static int abs(int x) {
        if (x >= 0) return x;
        else return -x;
    }

    public static int abs(int x, int y) {
        return abs(x - y);
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
}
