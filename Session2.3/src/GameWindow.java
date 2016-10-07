import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


/**
 * Created by Le Huy Duc on 02/10/2016.
 */
public class GameWindow extends Frame implements Runnable {
    //**********
    //********** IMAGES/DRAWING VARIABLES  ***************************************************************
    Image backgroundImage = Object.BACKGROUND_IMAGE;
    Image backBufferImage = null;
    private final int BACKGROUND_WIDTH = Object.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = Object.BACKGROUND_HEIGHT;
    public static int delay = 19;

    //********** PLAYER VARIABLES  ***************************************************************


    //**********  LOADING IMAGE  ***************************************************************
    public void loadAllImage() {
        System.out.println("Loaded images");
        backgroundImage = Self.getImage("resources/background.png");
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
        loadAllImage();
        Object.initAll();

        MultipleKeyListener mkl = new MultipleKeyListener();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                mkl.keyPressed(e);
                if (e.getKeyCode()==KeyEvent.VK_M) System.out.println("" + Object.countBullet);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                mkl.keyReleased(e);
            }
        });

        MultipleMouseListener mel = new MultipleMouseListener();
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mel.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mel.mouseMoved(e);
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mel.mouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mel.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mel.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        Explosion.initExplosion();
     //   repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }


    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        backBufferGraphics.drawImage(backgroundImage, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, this);

        for (int i=0;i<Object.countBullet;i++)
            Object.Bullets[i].drawImage(backBufferGraphics);

        for (int i =0;i<Object.countBot;i++)
            Object.Bots[i].drawImage(backBufferGraphics);

        for (int i = 0; i < Object.countMyPlane; i++) if (!Object.myPlanes[i].getDead())
            Object.myPlanes[i].drawImage(backBufferGraphics);

        g.drawImage(backBufferImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);
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

