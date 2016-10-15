package main;

import java.awt.*;

/**
 * Created by Le Huy Duc on 15/10/2016.
 */
public class GameWin {

    GameWin() {

    }

    public void draw(Graphics g) {
        Font fnt0 = new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.GREEN);
        g.drawString("YOU",500,300);
        g.drawString("WON",500,400);
    }


}
