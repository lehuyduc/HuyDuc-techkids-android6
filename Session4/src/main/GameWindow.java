package main;

import controllers.*;
import controllers.Enemy.EnemyPlaneController;
import controllers.Enemy.EnemyPlaneControllerManager;
import utilities.Utils;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class GameWindow extends Frame implements Runnable {

    public static final int BACKGROUND_WIDTH = 1200, BACKGROUND_HEIGHT = 700;
    public static final int delay = 18;
    public static final int animationDelay = 100;

    BufferedImage backBufferImage;
    Image background;
    BombController bc;

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

        this.addKeyListener(PlaneController.instance1.kil);

        this.addMouseListener(PlaneController.instance2.mil);

        this.addMouseMotionListener(PlaneController.instance2.mil);

        for (int i=0;i<8;i++) EnemyPlaneControllerManager.instance.add(new EnemyPlaneController(i*100+100,0));

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

        ControllerController.instance.draw(backBufferGraphics);

        if (bc!=null) bc.draw(backBufferGraphics);

        g.drawImage(backBufferImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);
    }

    static int count = 0;
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(delay);
                count++;

                if (count == 10*delay) {
                    count = 0;
                    Random rd = new Random();
                    bc = new BombController(rd.nextInt(800)+300,rd.nextInt(300)+300);
                }
                GameLevel.level1();
                ControllerController.instance.run();

                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


