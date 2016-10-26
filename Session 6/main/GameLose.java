package main;

import java.awt.*;

/**
 * Created by Le Huy Duc on 14/10/2016.
 */
public class GameLose {

    public GameLose() {

    }

    public void draw(Graphics g) {
        Font fnt0 = new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.RED);
        g.drawString("GAME",500,300);
        g.drawString("OVER",500,400);
    }

}
