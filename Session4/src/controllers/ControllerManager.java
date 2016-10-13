package controllers;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class ControllerManager implements BaseController{

    private Vector<SingleController> singleControllers;

    public ControllerManager() {
        singleControllers = new Vector<>();
    }

    public boolean needDelete() {
        return singleControllers.size()==0;
    }


    public void add(SingleController sc) {
        singleControllers.add(sc);
    }

    public void remove(SingleController sc) {
        singleControllers.remove(sc);
    }

    public void remove() {
        Iterator<SingleController> it = singleControllers.iterator();
        while (it.hasNext()) {
            SingleController singleController = it.next();
            if (singleController.needDelete()) it.remove();
        }
    }


    public synchronized void draw(Graphics g) {
        for (SingleController singleController : singleControllers) singleController.draw(g);
    }

    public synchronized void run() {
        for (SingleController singleController : singleControllers)
            singleController.run();
    }

}
