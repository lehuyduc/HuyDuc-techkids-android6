package main;

import script.ScriptReader;
import utilities.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by Le Huy Duc on 26/10/2016.
 */
public class MenuGameScreen extends GameScreen {

    public static final int BACKGROUND_WIDTH = GameConfig.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = GameConfig.BACKGROUND_HEIGHT;
    private Image background;

    public MenuGameScreen(ScreenManager screenManager) {
        super(screenManager);
        background = Utils.getImage("game_menu.png");
    }

    @Override
    void init() {

    }

    @Override
    void update(Graphics g) {
        Font fnt0 = new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.GREEN);
        g.drawImage(background,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,null);
        g.drawString("PRESS ENTER TO PLAY",325,300);
    }

    @Override
    void run() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            this.screenManager.change(new PlayGameScreen(screenManager),true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
