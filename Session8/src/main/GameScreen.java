package main;

import main.ScreenManager;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Le Huy Duc on 25/10/2016.
 */
public abstract class GameScreen implements MouseListener, KeyListener, MouseMotionListener {

    protected ScreenManager screenManager;
    public GameScreen(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    abstract void init();
    abstract void update(Graphics g);
    abstract void run();
}
