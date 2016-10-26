package main;

/**
 * Created by Le Huy Duc on 25/10/2016.
 */
public interface ScreenManager {
//    private Stack<GameScreen> screenStack;
//
//    public ScreenManager() {
//        screenStack = new Stack<>();
//    }

//    public void change(GameScreen gameScreen, boolean addToStack) {
//        if (addToStack) screenStack.push(gameScreen);
//    }

    void change(GameScreen gameScreen, boolean addToStack);
}
