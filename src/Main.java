import com.google.gson.Gson;
import utilities.Game;
import view.HomeView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

//        Window.getInstance();
        Game game = new Game();
//        JPanel meow = new HomeView();

    }
}
