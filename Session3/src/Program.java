import control.GameWindow;

/**
 * Created by Le Huy Duc on 10/10/2016.
 */
public class Program {
    public static void main(String[] args) {
        System.out.println("hello world");

        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();
    }
}
