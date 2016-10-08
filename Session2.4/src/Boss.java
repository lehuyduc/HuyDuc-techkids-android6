import java.awt.*;

/**
 * Created by Le Huy Duc on 08/10/2016.
 */
public class Boss extends Bot{

    public static Boss toBoss(Bot other) {
        Boss t = new Boss();
        t.nPoints = other.nPoints; t.isBoss = true;
        for (int i=0;i<t.nPoints;i++) {t.points[i] = other.points[i]; t.points2[i] = other.points2[i];}
        t.x = other.x; t.y = other.y;
        t.cornerX = other.cornerX; t.cornerY = other.cornerY;
        t.sizeX = other.sizeX; t.sizeY = other.sizeY;
        t.photo = other.photo;
        t.horizon = other.horizon;
        t.dead = other.dead;
        t.moveSpeed = other.moveSpeed;
        t.attackSpeed = other.attackSpeed;
        t.health = other.health;
        t.damage = other.damage;
        return t;
    }

    public Boss() {
    }
}
