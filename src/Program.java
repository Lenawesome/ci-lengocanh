import touhou.GameWindow;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by huynq on 7/4/17.
 */
public class Program {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.loop();

        Vector<Integer> integers = new Vector<>();
        integers.add(10);
        integers.add(15);
        integers.add(-7);
        for(Integer i : integers){
            System.out.println(i);
            integers.add(90);
        }

    }
}
