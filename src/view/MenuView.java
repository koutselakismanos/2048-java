package view;

import utilities.Game;

import javax.swing.*;

public class MenuView extends JPanel {
    private JButton newGameButton;
    private JButton historyButton;
    private JButton exitButton;
    private JButton aboutButton;
    private JPanel mainPanel;
    private JLabel playerNameLabel;

    public MenuView() {
        initComponents();
    }

    private void initComponents() {
        add(mainPanel);

        newGameButton.addActionListener(e -> newGame());

        historyButton.addActionListener(e -> historyMenu());
        aboutButton.addActionListener(e -> about());

        exitButton.addActionListener(e -> System.exit(0));
    }

    /**
     * Navigate to new game view
     */
    private void newGame() {
        Game.BOARD_CONTROLLER.resetBoard();
        Game.WINDOW.newGame();
    }

    /**
     * Navigate to history Menu view
     */
    private void historyMenu() {
        Game.WINDOW.historyMenu();
    }

    /**
     * Navigate to about view
     */
    private void about() {
        Game.WINDOW.about();
    }

    public void setPlayerNameLabel(String playerName) {
        playerNameLabel.setText(playerName);
    }
}
