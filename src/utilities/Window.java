package utilities;

import view.BoardView;
import view.HistoryView;
import view.HomeView;
import view.MenuView;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static Window instance;
    private final int width = 1200;
    private final int height = width / 16 * 9; // get 16:9 aspect ratio

    private final BoardView boardView = new BoardView();
    private final HomeView homeView = new HomeView();
    private final HistoryView historyView = new HistoryView();
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
        mainPanel.add(historyView, "history_view");
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
        menuView.setPlayerNameLabel(Game.getPlayerName());
        requestFocusInWindow();
    }

    public void history() {
        cardLayout.show(mainPanel, "history_view");
        historyView.addHistoryButtons();
        requestFocusInWindow();
    }
}
