package script;

import controllers.managers.EnemyPlaneControllerManager;
import main.GameLevel;

/**
 * Created by Le Huy Duc on 18/10/2016.
 */
public class LEVEL extends Script {

    int level;

    public LEVEL() {
        level = ScriptReader.instance.nextInt();
    }

    public void run() {
        executed = true;
        if (EnemyPlaneControllerManager.instance.size()==0) {
            finished = true;
            GameLevel.currentLevel = level;
            for (int i=0;i<level;i++) GameLevel.level[i] = true;
        }
    }
}
