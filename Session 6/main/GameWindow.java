package main;

import controllers.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class GameWindow extends Frame implements Runnable, ScreenManager {

    public static final int BACKGROUND_WIDTH = GameConfig.BACKGROUND_WIDTH;
    public static final int BACKGROUND_HEIGHT = GameConfig.BACKGROUND_HEIGHT;

    BufferedImage backBufferImage;
    public static int state = 0;
//    GameMenu gameMenu = new GameMenu();
//    GamePlay gamePlay = new GamePlay();
//    GameLose gameLose = new GameLose();
//    GameWin gameWin = new GameWin();

    private GameScreen currentGameScreen = new PlayGameScreen(this);
    private Stack<GameScreen> screenStack = new Stack<>();

    public GameWindow() {
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH,BACKGROUND_HEIGHT);

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

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    if (state==2) {state = 0; return;}
                    if (state==3) {state = 0; return;}
                    if (state==0) {
//                        gamePlay.init();
                        state = 1;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) state = 0;
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        currentGameScreen.update(backBufferGraphics);
//        if (state==0) gameMenu.draw(backBufferGraphics);
//        else if (state==1) gamePlay.draw(backBufferGraphics);
//        else if (state==2) gameLose.draw(backBufferGraphics);
//        else if (state==3) gameWin.draw(backBufferGraphics);

        g.drawImage(backBufferImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(GameConfig.threadDelay);
//                if (state==1) gamePlay.run();
                currentGameScreen.run();
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void change(GameScreen newGameScreen, boolean addToStack) {
        if (currentGameScreen!=null) detach(currentGameScreen);

        if (addToStack && currentGameScreen!=null) {
            screenStack.push(currentGameScreen);
        }

        currentGameScreen = newGameScreen;
        attach(currentGameScreen);
    }
    
    public void attach(GameScreen newGameScreen) {
        this.addMouseListener(newGameScreen);
        this.addMouseMotionListener(newGameScreen);
        this.addKeyListener(newGameScreen);
    }
    
    public void detach(GameScreen oldGameScreen) {
        this.removeMouseListener(oldGameScreen);
        this.removeMouseMotionListener(oldGameScreen);
        this.removeKeyListener(oldGameScreen);
    }
}


