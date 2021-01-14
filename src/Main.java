import utilities.Game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        // set look and feel to the default of the operating system
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        Game game = new Game();
    }
}
