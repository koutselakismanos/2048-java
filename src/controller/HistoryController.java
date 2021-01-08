package controller;

import model.BoardSchema;
import model.HistorySchema;
import model.TileSchema;

import java.util.*;

public class HistoryController extends HistorySchema {
    public HistoryController() {
        System.out.println(this.boards);

    }

    public void addBoard(BoardSchema boardSchema) {
        this.boards.add(boardSchema);
    }

    public List<BoardSchema> getTiles() {
        return this.boards;
    }

    public void setBoards(List<BoardSchema> boards) {
        this.boards = boards;
    }

    // TODO: DELETE ME
    public void printBoards() {
        System.out.println("------------------------------------------------");
        for (BoardSchema board : boards) {
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
