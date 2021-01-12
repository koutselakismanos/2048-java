package utilities;

import view.BoardView;
import view.HomeView;
import view.MenuView;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class Window extends JFrame {
    private static Window instance;
    private final int width = 1200;
    private final int height = width / 16 * 9;

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

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

        mainPanel.add(new HomeView(), "home_view");
        mainPanel.add(new MenuView(), "menu_view");
        mainPanel.add(new BoardView(), "board_view");
        add(mainPanel);


        addKeyListener(Game.KEYBOARD); // key input events

        setVisible(true);
    }

    public void startGame() {
        cardLayout.show(mainPanel, "menu_view");
        requestFocusInWindow();
    }
}
