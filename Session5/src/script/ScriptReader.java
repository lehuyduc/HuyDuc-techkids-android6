package script;

import utilities.Utils;

import java.util.Scanner;

/**
 * Created by Le Huy Duc on 17/10/2016.
 */
public class ScriptReader {

    private Scanner input = null;

    private ScriptReader() {
        input = Utils.getScanner("resources/GameScript.txt");
    }


    public int nextInt() {
        return input.nextInt();
    }

    public long nextLong() {
        return input.nextLong();
    }

    public String next() {
        return input.next();
    }

    public Script nextScript() {
        if (!input.hasNext()) return new Script();
        String type = input.next();
        ScriptType tp = ScriptType.valueOf(type);
        if (tp==ScriptType.CREATE) return new CREATE();
        if (tp==ScriptType.LEVEL) return new LEVEL();
        if (tp==ScriptType.WAIT) return new WAIT();
        if (tp==ScriptType.REPEAT) return new REPEAT();
        if (tp==ScriptType.END_SCOPE) return new END_SCOPE();
        return new Script();
    }

    public void init() {
        input = Utils.getScanner("resources/GameScript.txt");
    }

    public void run() {

    }

    public static final ScriptReader instance = new ScriptReader();
}
