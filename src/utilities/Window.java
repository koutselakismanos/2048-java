package utilities;

import model.GameFileModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static Window instance;
    private final int width = 1280;
    private final int height = 720;

    private final BoardView boardView = new BoardView(Game.BOARD_CONTROLLER, true);
    private final HomeView homeView = new HomeView();
    private final HistoryMenuView historyMenuView = new HistoryMenuView();
    private final HistoryBoardView historyBoardView = new HistoryBoardView();
    private final AboutView aboutView = new AboutView();
    private final MenuView menuView = new MenuView();

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private Window() {
        initComponents();
    }

    public static Window getInstance() {
        if (instance == null) {
            instance = new Window();
        }

        return instance;
    }

    /**
     * Init all the panels and attach Keyboard event listener
     */
    private void initComponents() {
        setTitle("2048");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // open window at center of screen

        mainPanel.add(homeView, "home_view");
        mainPanel.add(menuView, "menu_view");
        mainPanel.add(historyMenuView, "history_menu_view");
        mainPanel.add(historyBoardView, "history_board_view");
        mainPanel.add(aboutView, "about_view");
        mainPanel.add(boardView, "board_view");
        add(mainPanel);

        addKeyListener(Game.KEYBOARD); // key input events

        setVisible(true);
    }

    /**
     * Get player name and Navigate to main menu
     */
    public void startGame() {
        cardLayout.show(mainPanel, "menu_view");
        menuView.setPlayerNameLabel(Game.getPlayerName());
        requestFocusInWindow();
    }

    /**
     * Navigate to Game view
     */
    public void newGame() {
        cardLayout.show(mainPanel, "board_view");
        requestFocusInWindow();
    }


    /**
     * Navigate to Main menu
     */
    public void mainMenu() {
        cardLayout.show(mainPanel, "menu_view");
        requestFocusInWindow();
    }

    /**
     * Navigate to History menu
     */
    public void historyMenu() {
        cardLayout.show(mainPanel, "history_menu_view");
        historyMenuView.addHistoryButtons();
        requestFocusInWindow();
    }

    /**
     * Navigate to About view
     */
    public void about() {
        cardLayout.show(mainPanel, "about_view");
        requestFocusInWindow();
    }

    /**
     * Navigate to Game file History Board
     */
    public void historyBoard(GameFileModel gameFile) {
        cardLayout.show(mainPanel, "history_board_view");
        historyBoardView.loadGame(gameFile);
        requestFocusInWindow();
    }
}
