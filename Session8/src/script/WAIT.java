package script;

import controllers.managers.EnemyPlaneControllerManager;

/**
 * Created by Le Huy Duc on 18/10/2016.
 */
public class WAIT extends Script{
    private long start = 0, duration = 0;

    public WAIT() {
        duration = ScriptReader.instance.nextInt();
    }

    public void run() {
        if (!executed) start = System.currentTimeMillis();
        executed = true;
        long now = System.currentTimeMillis();
        if (duration >= 0) if (now - start >= duration) finished = true;
        if (duration==-1 && EnemyPlaneControllerManager.instance.size()==0) finished = true;
    }
}
