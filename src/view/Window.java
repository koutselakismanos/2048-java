package view;

import controller.Game;

import javax.swing.*;

public class Window extends JFrame {
    private static Window instance;
    private final int width = 1200;
    private final int height = width / 16 * 9;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Window() {
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
        setLocationRelativeTo(null);

        add(new BoardView());
        addKeyListener(Game.KEYBOARD); // key input events

        setVisible(true);
    }
}
