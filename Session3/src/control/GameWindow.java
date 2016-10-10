package control;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import controllers.*;
import models.*;
import utilities.*;
import views.*;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class GameWindow extends Frame implements Runnable {
    public static int BACKGROUND_WIDTH = 1200, BACKGROUND_HEIGHT = 700;
    public static Image BACKGROUND_IMAGE = null;
    public static int delay = 25;
    Image backBufferImage = null;

    PlaneController planeController = null, planeController2 = null;
    EnemyPlaneController[] enemyPlaneControllers = new EnemyPlaneController[30];
    public static int nEnemy = 7;
    boolean mouseAttacking = false;


    public void initImage() {
        BACKGROUND_IMAGE = Utils.getImage("resources/background.png");
        backBufferImage = new BufferedImage(BACKGROUND_WIDTH,BACKGROUND_HEIGHT,BufferedImage.TYPE_INT_ARGB);
    }

    public void initPlanes() {
        planeController = new PlaneController(
                new Plane(600,300),
                new PlaneView(Utils.getImage("resources/plane1.png"))
        );
        planeController2 = new PlaneController(
                new Plane(600,300),
                new PlaneView(Utils.getImage("resources/plane2.png"))
        );
    }

    public GameWindow() {
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH,BACKGROUND_HEIGHT);

        //*********** INITIALIZATION
        initImage();
        initPlanes();

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closing game");
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

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                planeController.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                planeController2.mouseMoved(e);
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("mouse pressed");
                mouseAttacking = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("mouse released");
                mouseAttacking = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        for (int i=0;i<nEnemy;i++) enemyPlaneControllers[i] = new EnemyPlaneController(
                new EnemyPlane(i*100+70,100),
                new EnemyPlaneView("resources/enemy_plane_white_2.png")
        );

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(BACKGROUND_IMAGE,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);

        planeController.drawImage(backBufferGraphics);
        planeController2.drawImage(backBufferGraphics);

        for (int i = 0;i<nEnemy;i++) if (enemyPlaneControllers[i]!=null)
            enemyPlaneControllers[i].drawImage(backBufferGraphics);

        g.drawImage(backBufferImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);
    }

    boolean remake = false;
    long needRemake = 0;
    void runAll() {
        planeController.run();
        planeController2.run();

        if (mouseAttacking) planeController2.attack();

        for (int i = 0;i<nEnemy;i++) if (enemyPlaneControllers[i]!=null)
            if (!enemyPlaneControllers[i].destruct()) enemyPlaneControllers[i].run();
            else enemyPlaneControllers[i] = null;
    }


    int count = 0;
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(delay+1);

                long now = System.currentTimeMillis();
                if (now >= needRemake && remake) {
                    remake = false;
//                    for (int i=0;i<nEnemy;i++) enemyPlaneControllers[i] = new EnemyPlaneController(
//                            new EnemyPlane(i*100+70,100),
//                            new EnemyPlaneView("resources/enemy_plane_white_2.png")
//                    );
                }
                runAll();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
