import com.google.gson.Gson;
import utilities.Game;
import view.HomeView;

import javax.swing.*;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

//        Window.getInstance();
//        new Game();
        JFrame meow = new HomeView();

    }
}
