package controller;

import model.BoardModel;
import model.HistoryModel;

import java.util.*;

public class HistoryController extends HistoryModel {
    public void addBoard(BoardModel boardModel) {
        this.boards.add(boardModel);
    }

    public List<BoardModel> getTiles() {
        return this.boards;
    }

    public void setBoards(List<BoardModel> boards) {
        this.boards = boards;
    }

    // TODO: DELETE ME
    public void printBoards() {
        System.out.println("------------------------------------------------");
        for (BoardModel board : boards) {
            for (int k = 0; k < board.boardSize; k++) {
                for (int j = 0; j < board.boardSize; j++) {
                    if (board.tiles.get(j).get(k) != null) {
                        System.out.print(board.tiles.get(j).get(k).getValue());
                    } else {
                        System.out.print(0);
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
