package views;

import models.GameObject;

import java.awt.*;

/**
 * Created by Le Huy Duc on 13/10/2016.
 */
public interface GameView {
    void setImage(String link);
    void drawImage(Graphics g, GameObject go);
    void run();
}
