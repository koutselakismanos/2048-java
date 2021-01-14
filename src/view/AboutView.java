package view;

import utilities.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutView extends JPanel {
    private JTextArea textArea;
    private JPanel mainPanel;
    private JButton backButton;

    public AboutView() {
        add(mainPanel);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.WINDOW.mainMenu();
            }
        });
    }
}
