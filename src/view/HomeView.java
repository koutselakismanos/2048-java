package view;

import utilities.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HomeView extends JPanel {
    private JTextField playerNameTextField;
    private JPanel mainPanel;
    private JButton startButton;

    public HomeView() throws HeadlessException {
        initComponents();
    }

    private void initComponents() {
        add(mainPanel);

        startButton.addActionListener(e -> startGame());

        playerNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();

                // On enter key submit input
                if (key == KeyEvent.VK_ENTER) {
                    startGame();
                }
            }
        });
    }

    private void startGame() {
        if (!playerNameTextField.getText().equals("")) {
            Game.setPlayerName(playerNameTextField.getText());
            Game.WINDOW.startGame();
        }
    }

}
