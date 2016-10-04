import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * Created by Le Huy Duc on 02/10/2016.
 */
public class GameWindow extends Frame {

    // Images variable
    Image backgroundImage = null;
    private final int backgroundX = 600, backgroundY = 400;
    private int plane1X = 150, last1X = 150;
    private int plane1Y = 250, last1Y = 250;
    private int plane2X = 300, last2X = 300;
    private int plane2Y = 250, last2Y = 250;
    private int plane1SizeX = 60, plane1SizeY = 60;
    private int plane2SizeX = 60, plane2SizeY = 60;
    //Về nhà em làm class plane sau ạ :v bay giờ dùng tạm bién



    //* plane movement variables
    private int moveSpeed = 3;
    private final int limit = 6; // Repaint the frame if the plane1 has moved limit pixels from the last position
    // to make movement smoother.
    long lastDraw = System.currentTimeMillis();
    // Về nhà làm thêm swing timer để keypress không bị delay sau phím đầu

    public int abs(int x,int y) {
        if (x >= y) return x - y;
        else return y - x;
    }

    public void updateWindow() {
        last1X = plane1X; last1Y = plane1Y;
        last2X = plane2X; last2Y = plane2Y;
        repaint();
    }

    public void moveplane1Key(KeyEvent e,boolean paintNow) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                System.out.println("Key right");
                plane1X += moveSpeed;
                if (plane1X >= backgroundX) plane1X = backgroundX;
                break;

            case KeyEvent.VK_LEFT:
                System.out.println("Key left");
                plane1X -= moveSpeed;
                if (plane1X < 0) plane1X = 0;
                break;

            case KeyEvent.VK_DOWN:
                System.out.println("Key down");
                plane1Y += moveSpeed;
                if (plane1Y >= backgroundY) plane1Y = backgroundY;
                break;

            case KeyEvent.VK_UP:
                System.out.println("Key up");
                plane1Y -= moveSpeed;
                if (plane1Y < 0) plane1Y = 0;
                break;
        }

        long now = System.currentTimeMillis();
        boolean late = (now - lastDraw) >= 100;

        if (abs(plane1X,last1X) + abs(plane1Y,last1Y) >= 0 || paintNow || late) {
            updateWindow();
            lastDraw = System.currentTimeMillis();
        }
    }

    public GameWindow() {
        this.setVisible(true);
        this.setSize(backgroundX,backgroundY);

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

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Key typed");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key pressed");
                moveplane1Key(e,false);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Key released");
                moveplane1Key(e,true);
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println(e.getLocationOnScreen());
                Point p = e.getLocationOnScreen();
                plane2X = p.x; plane2Y = p.y;
                if (abs(plane2X,last2X) + abs(plane2Y,last2Y) >= 2*limit) updateWindow();
            }

            public void mouseStopped(MouseEvent e) {
                if (last2X==plane2X && last2Y==plane2Y) return;
                System.out.println("Mouse stopped");
                Point p = e.getLocationOnScreen();
                plane2X = p.x; last2X = plane2X;
                plane2Y = p.y; last2Y = plane2Y;
                updateWindow();
            }
        });

        try {
            backgroundImage = ImageIO.read(new File("resources/background.png"));
            System.out.println("Loaded backgroundImage");
        } catch (IOException e) {
            e.printStackTrace();
        }

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage,0,0,backgroundX,backgroundY,null);
        System.out.println("Drawing backgroundImage");

        Image plane1 = null;
        Image plane2 = null;
        try {
            plane1 = ImageIO.read(new File("resources/plane1.png"));
            System.out.println("Drawing plane1");
            plane2 = ImageIO.read(new File("resources/plane2.png"));
            System.out.println("Drawing plane2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(plane1,plane1X,plane1Y,plane1SizeX,plane1SizeY,null);
        g.drawImage(plane2,plane2X,plane2Y,plane2SizeX,plane2SizeY,null);
    }

}


