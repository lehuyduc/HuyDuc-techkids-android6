package controllers;

import models.Bullet;
import models.Plane;
import views.PlaneView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class PlaneController {
    private Plane plane = null;
    private PlaneView planeView = null;
    private Vector<BulletController> bulletControllers = new Vector<>();
    long lastAttack = 0;

    public void drawImage(Graphics g) {
        planeView.drawImage(g,plane);
        for (BulletController bulletController : bulletControllers) bulletController.drawImage(g);
    }

    public void keyPressed(KeyEvent e) {
        plane.movePlaneKey(e.getKeyCode());
        if (e.getKeyCode()==KeyEvent.VK_SPACE) attack();
    }

    public void mouseMoved(MouseEvent e) {
        plane.movePlaneMouse(e);
    }

    public void attack() {
        long now = System.currentTimeMillis();
        if (now - lastAttack < plane.getAttackSpeed()) return;
        lastAttack = now;
        BulletController bulletController = new BulletController(plane.getX(),plane.getY()-plane.getSizeY()/2,false);
        bulletControllers.add(bulletController);
    }

    public PlaneController(Plane plane,PlaneView planeView) {
        this.plane = plane;
        this.planeView = planeView;
    }

    public void run() {
        for (BulletController bulletController : bulletControllers) bulletController.run();
    }
}
