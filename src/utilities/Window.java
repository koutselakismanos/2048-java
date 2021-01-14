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
        mainPanel.add(boardView, "board_view");
        add(mainPanel);


        addKeyListener(Game.KEYBOARD); // key input events

        setVisible(true);
    }

    public void startGame() {
        cardLayout.show(mainPanel, "menu_view");
        menuView.setPlayerNameLabel(Game.getPlayerName());
        requestFocusInWindow();
    }

    public void newGame() {
        cardLayout.show(mainPanel, "board_view");
        requestFocusInWindow();
    }

    public void mainMenu() {
        cardLayout.show(mainPanel, "menu_view");
        requestFocusInWindow();
    }

    public void historyMenu() {
        cardLayout.show(mainPanel, "history_menu_view");
        historyMenuView.addHistoryButtons();
        requestFocusInWindow();
    }

    public void historyBoard(GameFileModel gameFile) {
        cardLayout.show(mainPanel, "history_board_view");
        historyBoardView.loadGame(gameFile);
        requestFocusInWindow();
    }
}
