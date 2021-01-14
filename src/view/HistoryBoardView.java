package view;

import controller.BoardController;
import model.GameFileModel;
import model.HistoryModel;
import utilities.Game;

import javax.swing.*;
import java.awt.*;

public class HistoryBoardView extends JPanel {
    private JPanel mainPanel;
    private JSlider turnSlider;
    private JPanel boardPanel;
    private JButton backButton;
    private BoardController boardController;
    private HistoryModel historyModel;

    public HistoryBoardView() {
        add(mainPanel);

        turnSlider.addChangeListener(e -> changeBoard());
        backButton.addActionListener(e -> Game.WINDOW.historyMenu());
    }

    /**
     * On JSlider change show the the matching board
     */
    public void changeBoard() {
        boardController.setBoard(historyModel.boards.get(turnSlider.getValue()));
        Game.WINDOW.repaint();
    }

    /**
     * Load game file
     */
    public void loadGame(GameFileModel gameFile) {
        turnSlider.setValue(0);
        historyModel = Game.DATABASE.loadGame(gameFile.getIndex());
        turnSlider.setMaximum(historyModel.boards.size() - 1);
    }

    private void createUIComponents() {
        turnSlider = new JSlider(0, 10);
        turnSlider.setValue(0);
        turnSlider.setLabelTable(null);
        turnSlider.setPaintTicks(true);
        turnSlider.setPaintLabels(true);

        boardController = new BoardController(0);
        BoardView boardView = new BoardView(boardController);
        boardView.setBoardDimensions(200);
        boardPanel = boardView;
        boardPanel.setPreferredSize(new Dimension(1164, 581));
    }
}
