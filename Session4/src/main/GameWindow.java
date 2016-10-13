package main;

import controllers.CollisionPool;
import controllers.EnemyPlaneController;
import controllers.PlaneController;
import models.EnemyPlane;
import utilities.Utils;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class GameWindow extends Frame implements Runnable {

    public static final int BACKGROUND_WIDTH = 1200, BACKGROUND_HEIGHT = 700;

    BufferedImage backBufferImage;
    Image background;
    PlaneController planeController = new PlaneController(500,500,false);
    PlaneController planeController2 = new PlaneController(700,500,true);
    EnemyPlaneController[] enemyPlaneControllers = new EnemyPlaneController[10];

    public GameWindow() {
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH,BACKGROUND_HEIGHT);

        background = Utils.getImage("resources/background.png");
        backBufferImage = new BufferedImage(BACKGROUND_WIDTH,BACKGROUND_HEIGHT,BufferedImage.TYPE_INT_RGB);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.addKeyListener(planeController.kil);

        this.addMouseListener(planeController2.mil);

        this.addMouseMotionListener(planeController2.mil);

        for (int i=0;i<8;i++) enemyPlaneControllers[i] = new EnemyPlaneController(i*100+100,0);

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(background,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);

        planeController.draw(backBufferGraphics);
        planeController2.draw(backBufferGraphics);
        for (int i=0;i<8;i++) enemyPlaneControllers[i].draw(backBufferGraphics);

        g.drawImage(backBufferImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(40);
                planeController.run();
                planeController2.run();
                for (int i=0;i<8;i++) enemyPlaneControllers[i].run();
                CollisionPool.instance.run();

                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


