package utilities;

import controller.BoardController;
import controller.HistoryController;
import database.Database;

public class Game {
    private static String playerName = "";

    public static final Database DATABASE = Database.getInstance();
    public static final HistoryController HISTORY_CONTROLLER = new HistoryController();
    public static final BoardController BOARD_CONTROLLER = new BoardController(6);
    public static final Keyboard KEYBOARD = new Keyboard();
    public static final Window WINDOW = Window.getInstance();

    public static void setPlayerName(String name) {
        playerName = name;
    }

    public static String getPlayerName() {
        return playerName;
    }
}
