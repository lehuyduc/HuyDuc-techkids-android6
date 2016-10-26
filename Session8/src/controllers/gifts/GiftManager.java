package controllers.gifts;

import controllers.managers.ControllerManager;

import java.awt.*;

/**
 * Created by Le Huy Duc on 18/10/2016.
 */
public class GiftManager extends ControllerManager {

    public void draw(Graphics g) {
        super.draw(g);
    }

    public void run() {
        super.run();
    }

    public static final GiftManager instance = new GiftManager();
}
