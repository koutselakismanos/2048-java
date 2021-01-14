package view;

import controller.BoardController;
import model.GameFileModel;
import model.HistoryModel;
import utilities.Game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class HistoryBoardView extends JPanel {
    private JPanel mainPanel;
    private JSlider turnSlider;
    private JPanel boardPanel;
    private BoardController boardController;
    private HistoryModel historyModel;

    public HistoryBoardView() {
        add(mainPanel);

        turnSlider.addChangeListener(e -> changeBoard());
    }

    public void changeBoard() {
        boardController.setBoard(historyModel.boards.get(turnSlider.getValue()));
        Game.WINDOW.repaint();
    }

    public void loadGame(GameFileModel gameFile) {
        historyModel = Game.DATABASE.loadGame(gameFile.getIndex());
        turnSlider.setMaximum(historyModel.boards.size() - 1);
    }

    private void createUIComponents() {
        turnSlider = new JSlider(0, 10);
        turnSlider.setValue(0);

        boardController = new BoardController(0);
        BoardView boardView = new BoardView(boardController);
        boardView.setBoardDimensions(200);
        boardPanel = boardView;
        boardPanel.setPreferredSize(new Dimension(1164, 581));
    }
}
