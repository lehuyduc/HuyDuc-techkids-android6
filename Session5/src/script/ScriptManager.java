package script;

import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane;

import java.util.Vector;

/**
 * Created by Le Huy Duc on 18/10/2016.
 */
public class ScriptManager {
    private Vector<Script> scripts;
    private boolean finished = false;

    public void init() {
        finished = false;
        for (Script script : scripts) script.init();
    }

    public ScriptManager() {
        scripts = new Vector<>();
        init();
    }

    public int size() {return scripts.size();}

    public void add(Script sc) {
        scripts.add(sc);
    }

    public boolean isFinished() {return finished;}


    public void run() {
        int counter = 0;
        for (Script script : scripts) {
            if (!script.isFinished()) script.run();
            if (!script.isFinished()) break;
            counter++;
        }
        if (counter==scripts.size()) finished = true;
    }
}
