import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by Le Huy Duc on 02/10/2016.
 */
public class GameWindow extends Frame implements Runnable {
    //**********
    //********** IMAGES/DRAWING VARIABLES  ***************************************************************
    Image backgroundImage = Object.BACKGROUND_IMAGE;
    Image backBufferImage = null;
    private final int BACKGROUND_WIDTH = Object.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = Object.BACKGROUND_HEIGHT;
    public static int delay = 20;

    //********** PLAYER VARIABLES  ***************************************************************
    public static MyPlane[] myPlanes = new MyPlane[MyPlane.MAX_PLANE];
    public static int countMyPlane = 0;

    //********** IMPORTANT VARIABLES  ***************************************************************



    //********** SELFMADE FUNCTIONS  ***************************************************************
    public int abs(int x) {if (x>=0) return x; else return -x;}

    public int abs(int x,int y) {return abs(x-y);}


    //**********  LOADING IMAGE  ***************************************************************
    public Image getImage(String link) {
        Image res = null;
        try {
            res = ImageIO.read(new File(link));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void loadAllImage() {
        System.out.println("Loaded images");
        backgroundImage = getImage("resources/background.png");
        myPlanes[0].setPhoto("resources/plane1.png");
        myPlanes[1].setPhoto("resources/plane2.png");
    }


    //********** INITIALIZING PLANES
    public void initMyPlanes() {
        countMyPlane = 2;
        for (int i=0;i<countMyPlane;i++) myPlanes[i] = new MyPlane(0,0,null);

        loadAllImage();

        myPlanes[0].setX(100);
        myPlanes[0].setY(300);
        myPlanes[0].setMoveSpeed(10);

        myPlanes[1].setX(400);
        myPlanes[1].setY(300);
        myPlanes[1].setUseMouse(true);
        myPlanes[1].setMoveSpeed(3);
    }


    //********** MAIN FUNCTION ***************************************************************
    public GameWindow() {

        backBufferImage = new BufferedImage(BACKGROUND_WIDTH,BACKGROUND_HEIGHT,BufferedImage.TYPE_INT_ARGB);


        //**********  GAME WINDOW SETTING  ***************************************************************
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH,BACKGROUND_HEIGHT);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowopening");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowclosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowclosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("windowiconfied");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("windowdeiconfied");
            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        //**********  PLAYER MOVEMENT  ***************************************************************
        initMyPlanes();
        Object.initAll();

        MultipleKeyListener mkl = new MultipleKeyListener();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                mkl.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                mkl.keyReleased(e);
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                for (int i=0;i<countMyPlane;i++)
                    if (myPlanes[i].getUseMouse()) myPlanes[i].movePlaneMouse(e);
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i=0;i<countMyPlane;i++) if (myPlanes[i].getUseMouse()) myPlanes[i].attack();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //**********  SETTING FRAME PER SECOND  ***************************************************************
//        ActionListener taskPerformer = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                repaint();
//            }
//        };
//
//        int delay = 115;
//        new Timer(delay,taskPerformer).start();

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("Drawing Images");
    }


    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(backgroundImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);

        for (int i=0;i<Object.countBullet;i++)
            Object.Bullets[i].drawImage(backBufferGraphics);

        for (int i = 0; i < countMyPlane; i++)
            myPlanes[i].drawImage(backBufferGraphics);

        g.drawImage(backBufferImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,null);
    }



    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(delay+1);
                Object.updateAll();
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


