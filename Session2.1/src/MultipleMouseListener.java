import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Le Huy Duc on 05/10/2016.
 */
public class MultipleMouseListener implements MouseMotionListener, MouseListener {

    private boolean pressed = false;
    private MouseEvent me;

    @Override
    public void mouseDragged(MouseEvent e) {
        pressed = true;
        me = e;
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
        pressed = true;
        me = e;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        me = e;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    //**********  TIMER PART **********************************************
    public MultipleMouseListener() {

        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i<GameWindow.countMyPlane;i++) {
                    MyPlane t = GameWindow.myPlanes[i];
                    if (t.getUseMouse()) {
                        if (me!=null) t.movePlaneMouse(me);
                        if (pressed) t.attack();
                    }
                }
            }
        };

        new Timer(GameWindow.delay, taskPerformer).start();
    }
}
