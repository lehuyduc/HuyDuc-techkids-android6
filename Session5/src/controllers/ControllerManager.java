package controllers;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class ControllerManager implements BaseController{

    protected Vector<SingleController> singleControllers;

    public ControllerManager() {
        singleControllers = new Vector<>();
    }

    public boolean deleteNow() {
        return singleControllers.size()==0;
    }

    public int size() {return singleControllers.size();}

    public synchronized void add(SingleController sc) {
        singleControllers.add(sc);
        if (sc instanceof Colliable) CollisionManager.instance.add((Colliable)sc);
    }

    public synchronized void remove(SingleController sc) {
        singleControllers.remove(sc);
    }

    public synchronized void clear() {
        singleControllers.clear();
    }

    public synchronized void remove() {
        Iterator<SingleController> it = singleControllers.iterator();
        while (it.hasNext()) {
            SingleController singleController = it.next();
            if (singleController.deleteNow()) it.remove();
        }
    }


    public synchronized void draw(Graphics g) {
        for (SingleController singleController : singleControllers) singleController.draw(g);
    }

    public synchronized void run() {
        for (SingleController singleController : singleControllers)
            singleController.run();
        remove();
    }

}
