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

        exitButton.addActionListener(e -> System.exit(0));

    }

    private void newGame() {
        Game.BOARD_CONTROLLER.resetBoard();
        Game.WINDOW.newGame();
    }

    private void historyMenu() {
        Game.WINDOW.historyMenu();
    }

    public void setPlayerNameLabel(String playerName) {
        playerNameLabel.setText(playerName);
    }
}
