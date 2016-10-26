package main;

import controllers.*;
import utilities.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    private GameScreen currentGameScreen = new MenuGameScreen(this);
    private Stack<GameScreen> screenStack = new Stack<>();
    private boolean inGameScreen = false;

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

        this.addKeyListener(currentGameScreen);
        this.addMouseListener(currentGameScreen);
        this.addMouseMotionListener(currentGameScreen);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE) back();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        repaint();
        Utils.playSound("Glorious_morning.wav",true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {

        Graphics backBufferGraphics = backBufferImage.getGraphics();

        currentGameScreen.update(backBufferGraphics);

        g.drawImage(backBufferImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(GameConfig.threadDelay);

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

    public void back() {
        if (screenStack.size() > 0) {
            GameScreen newGameScreen = screenStack.pop();
            detach(currentGameScreen);
            attach(newGameScreen);
        }
    }
    
    public void attach(GameScreen newGameScreen) {
        this.currentGameScreen = newGameScreen;
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


