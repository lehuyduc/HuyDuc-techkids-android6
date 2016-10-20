package controllers.gifts;

import controllers.SingleControllerManager;

import java.awt.*;

/**
 * Created by Le Huy Duc on 18/10/2016.
 */
public class GiftManagerSingle extends SingleControllerManager {

    public void draw(Graphics g) {
        super.draw(g);
    }

    public void run() {
        super.run();
    }

    public static final GiftManagerSingle instance = new GiftManagerSingle();
}
