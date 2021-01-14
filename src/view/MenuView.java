package view;

import utilities.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JPanel {
    private JButton newGameButton;
    private JButton historyButton;
    private JButton exitButton;
    private JButton aboutButton;
    private JPanel mainPanel;
    private JLabel playerNameLabel;

    public MenuView() {
        add(mainPanel);

        newGameButton.addActionListener(e -> newGame());

        historyButton.addActionListener(e -> history());
    }

    private void newGame() {
        Game.BOARD_CONTROLLER.resetBoard();
        Game.WINDOW.newGame();
    }

    private void history() {
        Game.WINDOW.history();
    }

    public void setPlayerNameLabel(String playerName) {
        playerNameLabel.setText(playerName);
    }
}
