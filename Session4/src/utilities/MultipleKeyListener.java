package utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class MultipleKeyListener implements KeyListener{

    // SET OF CURRENTLY PRESSED KEY
    private Set<Integer> pressed = new HashSet<Integer>();

    @Override
    public synchronized void keyTyped(KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }



    public MultipleKeyListener() {

    }
}
