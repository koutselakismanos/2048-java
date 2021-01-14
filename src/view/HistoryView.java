package view;

import model.GameFileModel;
import utilities.Game;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryView extends JPanel {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JPanel container;

    public HistoryView() throws HeadlessException {
        add(mainPanel);
        scrollPane.setViewportBorder(null);
    }

    public void addHistoryButtons() {
        container.removeAll();

        for (GameFileModel gameFile : getAllUserGames()) {
            Button button = new Button("Game " + gameFile.getIndex());
            button.setPreferredSize(new Dimension(150, 50));
            container.add(button);
        }
    }

    public List<GameFileModel> getAllUserGames() {
        return Game.DATABASE.getAllUserGames();
    }

    private void createUIComponents() {
        container = new JPanel();
        container.setLayout(new GridLayout(0, 1));
    }
}

