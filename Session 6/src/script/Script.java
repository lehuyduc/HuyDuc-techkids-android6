package script;

import java.util.Scanner;

/**
 * Created by Le Huy Duc on 17/10/2016.
 */
public class Script {

    protected boolean finished = false, executed = false;
    protected long startTime = 0;

    public Script() {

    }

    public Script(boolean v) {
        finished = v;
    }

    public boolean isExecuted() { return executed;}

    public boolean isFinished() {
        return finished;
    }

    public void setExecuted(boolean v) {executed = v;}

    public void setFinished(boolean v) {finished = v;}


    public void init() {
        executed = false;
        finished = false;
    }

    public void run() {

    }
}
