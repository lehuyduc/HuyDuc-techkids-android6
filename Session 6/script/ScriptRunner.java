package script;

/**
 * Created by Le Huy Duc on 18/10/2016.
 */
public class ScriptRunner {

    public Script currentScript;

    private ScriptRunner() {
        currentScript = new Script(true);
    }

    public void init() {
        ScriptReader.instance.init();
        currentScript = new Script(true);

    }

    public void run() {
        if (currentScript.isFinished()) currentScript = ScriptReader.instance.nextScript();
        currentScript.run();
    }

    public static final ScriptRunner instance = new ScriptRunner();
}
