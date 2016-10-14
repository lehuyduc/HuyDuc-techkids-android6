package controllers;

import models.GameObject;
import models.Plane;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class CollisionManager implements BaseController{

    private Vector<Colliable> list = new Vector<>();

    public void add(Colliable c) {
        list.add(c);
    }

    public synchronized void remove() {
        Iterator<Colliable> it = list.iterator();
        while (it.hasNext()) {
            Colliable c = it.next();
            if (c instanceof PlaneController) continue;
            if (c.getCollisionObject().getDead()) it.remove();
        }
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void run() {
        int n = list.size();
        for (int i=0;i<n-1;i++)
            for (int j=i+1;j<n;j++)
            {
                Colliable c1 = list.get(i);
                Colliable c2 = list.get(j);

                if (!c1.getCanCollide() || !c2.getCanCollide()) continue;

                GameObject g1 = c1.getCollisionObject();
                GameObject g2 = c2.getCollisionObject();

                if (g1.collide(g2)) {
                    c1.onCollide(c2);
                    c2.onCollide(c1);
                }
            }

        remove();
    }

    public static final CollisionManager instance = new CollisionManager();
}
