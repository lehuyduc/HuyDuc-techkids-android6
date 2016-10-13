package controllers;

import models.GameObject;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public class CollisionPool implements BaseController{

    private Vector<Colliable> pool = new Vector<>();

    public void add(Colliable c) {
        pool.add(c);
    }

    public void remove(Colliable c) {
        pool.remove(c);
    }

    public void remove() {
        Iterator<Colliable> it = pool.iterator();
        while (it.hasNext()) {
            Colliable c = it.next();
            if (c.getCollisionObject().getDead()) it.remove();
        }
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void run() {
        int n = pool.size();
        for (int i=0;i<n-1;i++)
            for (int j=i+1;j<n;j++)
            {
                Colliable c1 = pool.get(i);
                Colliable c2 = pool.get(j);
                if (!c1.canCollide || !c2.canCollide) continue;

                GameObject g1 = c1.getCollisionObject();
                GameObject g2 = c2.getCollisionObject();

                if (g1.collide(g2)) {
                    c1.onCollide(c2);
                    c2.onCollide(c1);
                }
            }

        remove();
    }

    public static final CollisionPool instance = new CollisionPool();
}
