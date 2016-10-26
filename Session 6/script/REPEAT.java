package script;

/**
 * Created by Le Huy Duc on 18/10/2016.
 */
public class REPEAT extends Script {
    private ScriptManager scriptManager;
    private int repeatTime = 0, hasRepeatTime = 0;

    public void init() {
        super.init();
        hasRepeatTime = 0;
    }

    public REPEAT() {
        scriptManager = new ScriptManager();
        repeatTime = ScriptReader.instance.nextInt();

        Script script = new Script();
        while (!(script instanceof END_SCOPE)) {
            script = ScriptReader.instance.nextScript();
            if (script instanceof END_SCOPE) break;
            scriptManager.add(script);
        }
    }

    public boolean isFinished() {return this.finished;}

    public void run() {
        executed = true;
        scriptManager.run();
        if (scriptManager.isFinished()) {hasRepeatTime++; scriptManager.init();}
        if (hasRepeatTime==repeatTime) finished = true;
    }
}
