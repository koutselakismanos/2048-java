package view;

import model.GameFileModel;
import utilities.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HistoryMenuView extends JPanel {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JPanel container;
    private JButton backButton;

    public HistoryMenuView() throws HeadlessException {
        add(mainPanel);

        scrollPane.setViewportBorder(null);

        backButton.addActionListener(e -> Game.WINDOW.mainMenu());
    }

    /**
     * List Player's game files
     */
    public void addHistoryButtons() {
        container.removeAll();

        for (GameFileModel gameFile : Game.DATABASE.getAllUserGames()) {
            Button button = new Button("Game " + gameFile.getIndex());
            button.setPreferredSize(new Dimension(150, 50));
            button.addActionListener(e -> Game.WINDOW.historyBoard(gameFile)
            );
            container.add(button);
        }
    }

    private void createUIComponents() {
        container = new JPanel();
        container.setLayout(new GridLayout(0, 1));
    }
}

