import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

/**
 * Created by Le Huy Duc on 05/10/2016.
 */
public class MultipleKeyListener implements KeyListener {

    // Set of current pressed key
    private Set<Integer> pressed = new HashSet<Integer>();

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public MultipleKeyListener() {

        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pressed.size() >= 1) {
                    for (Integer it : pressed) {
                        for (int i=0;i<GameWindow.countMyPlane;i++) {
                            MyPlane t = GameWindow.myPlanes[i];
                            if (!t.getUseMouse()) t.movePlaneKey(it);
                            if (it== VK_SPACE && !t.getUseMouse()) t.attack();
                        }
                    }
                }
            }
        };

        new Timer(GameWindow.delay, taskPerformer).start();
    }
}
