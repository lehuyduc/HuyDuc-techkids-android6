package main;

import controllers.PlayerController;
import main.GameConfig;
import models.Player;
import utilities.Utils;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by Le Huy Duc on 19/10/2016.
 */
public class GameWindow extends Frame implements Runnable {

    private int BACKGROUND_WIDTH = GameConfig.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = GameConfig.BACKGROUND_HEIGHT;

    BufferedImage backBufferImage = new BufferedImage(BACKGROUND_WIDTH,BACKGROUND_HEIGHT,BufferedImage.TYPE_INT_RGB);
    Image background = Utils.getImage("background.png");


    public GameWindow() {
        this.setVisible(true);
        this.setSize(BACKGROUND_WIDTH,BACKGROUND_HEIGHT);

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

        this.addKeyListener(PlayerController.instance.keyInputListener);

        PlayerController.instance.setColumn(5);
        PlayerController.instance.setRow(5);
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

        PlayerController.instance.draw(backBufferGraphics);

        g.drawImage(backBufferImage,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(25);
                GamePlay.playerTurn = true;
                PlayerController.instance.run();

                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
