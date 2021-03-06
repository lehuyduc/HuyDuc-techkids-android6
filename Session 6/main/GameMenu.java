package main;

import utilities.Utils;

import java.awt.*;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class GameMenu {

    public static final int BACKGROUND_WIDTH = GameConfig.BACKGROUND_WIDTH, BACKGROUND_HEIGHT = GameConfig.BACKGROUND_HEIGHT;
    private Image background;

    public GameMenu() {
        background = Utils.getImage("game_menu.png");
    }

    public void draw(Graphics g) {
        Font fnt0 = new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.GREEN);
        g.drawImage(background,0,0,BACKGROUND_WIDTH,BACKGROUND_HEIGHT,null);
        g.drawString("PRESS ENTER TO PLAY",325,300);
    }
}
