package controller;

import model.BoardModel;
import model.HistoryModel;

import java.util.*;

public class HistoryController extends HistoryModel {

    /**
     * Add a board to the history
     */
    public void addBoard(BoardModel boardModel) {
        this.boards.add(boardModel);
    }

    /**
     * Empty history
     */
    public void resetHistory() {
        this.boards = new ArrayList<>();
    }

    public List<BoardModel> getBoards() {
        return this.boards;
    }

    public void setBoards(List<BoardModel> boards) {
        this.boards = boards;
    }
}
