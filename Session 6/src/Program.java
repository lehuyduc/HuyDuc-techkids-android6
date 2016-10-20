import main.GameWindow;
import utilities.Utils;

import java.util.Scanner;

/**
 * Created by Le Huy Duc on 12/10/2016.
 */
public class Program {
    public static void main(String[] args) {
        System.out.println("hello world");
        GameWindow gameWindow = new GameWindow();

        Thread thread = new Thread(gameWindow);
        thread.start();

    }
}
