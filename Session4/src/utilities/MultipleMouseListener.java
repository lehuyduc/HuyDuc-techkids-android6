package utilities;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Le Huy Duc on 11/10/2016.
 */
public class MultipleMouseListener implements MouseListener, MouseMotionListener {

    private MouseEvent me = null;
    private boolean mousePressed = false;

    @Override
    public void mouseDragged(MouseEvent e) {
        me = e;
        mousePressed = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        me = e;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        me = e;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        me = e;
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public MultipleMouseListener() {

    }
}
