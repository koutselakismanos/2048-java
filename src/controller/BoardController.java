package controller;

import model.BoardSchema;
import model.TileSchema;
import utilities.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BoardController extends BoardSchema {

    public BoardController(int boardSize) {
        super();
        this.boardSize = boardSize;

        generateBoard();

        insertRandomTile();
        insertRandomTile();
        addBoardToHistory();
//        HistoryController historyController = new HistoryController();
//        historyController.printBoards();
//        history.printHistory();
//        historyController.saveBoard(model.getTiles());
    }

    public void generateBoard() {
        tiles = new ArrayList<>();

        for (int i = 0; i < boardSize; i++) {
            tiles.add(new ArrayList<>());
            for (int j = 0; j < boardSize; j++) {
                tiles.get(i).add(j, null);
            }
        }
    }

    // TODO: DELETE ME
    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(getTileValue(i, j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void insertRandomTile() {
        List<Point> emptyTiles = new ArrayList<>();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (getTileAt(i, j) == null) {
                    emptyTiles.add(new Point(i, j));
                }
            }
        }

        Random rd = new Random();
        if (emptyTiles.size() > 0) {
            int randomNumber = rd.nextInt(emptyTiles.size());
            Point newTilePoint = emptyTiles.get(randomNumber);

            int twoOrFour;
            int randomPercentage = rd.nextInt(100);
            if (randomPercentage < 90) { // 90% to draw a 2 tile, 10% a 4 tile
                twoOrFour = 2;
            } else {
                twoOrFour = 4;
            }

            setTileAt(newTilePoint.x, newTilePoint.y, twoOrFour);
        }
    }

    public void setTileAt(int x, int y, int value) {
        if (x >= boardSize || y >= boardSize) {
            return;
        }

        if (value == 0) {
            tiles.get(x).set(y, null);
            return;
        }

        tiles.get(x).set(y, new TileSchema(x, y, value));
    }

    public TileSchema getTileAt(int x, int y) {
        return tiles.get(x).get(y);
    }

    public void removeTileAt(int x, int y) {
        tiles.get(x).set(y, null);
    }

    public boolean isTileEmpty(int x, int y) {
        return tiles.get(x).get(y) == null;
    }

    public int getTileValue(int x, int y) {
        if (tiles.get(x).get(y) == null) {
            return 0;
        }

        return tiles.get(x).get(y).getValue();
    }

    public void shiftTilesUp() {
        int count;
        for (int i = 0; i < boardSize; i++) {
            count = 0;
            for (int j = 0; j < boardSize; j++) {
                if (!isTileEmpty(i, j)) {
                    setTileAt(i, count, getTileValue(i, j));
                    if (count < j) {
                        removeTileAt(i, j);
                    }
                    count++;
                }
            }
        }
    }

    public void mergeTilesUp() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize - 1; j++) {
                if (getTileValue(i, j) == getTileValue(i, j + 1)) {
                    setTileAt(i, j, getTileValue(i, j) * 2);
                    setTileAt(i, j + 1, 0);
                }
            }
        }
    }


    public void shiftTilesDown() {
        int count;
        for (int i = 0; i < boardSize; i++) {
            count = boardSize - 1;
            for (int j = boardSize - 1; j >= 0; j--) {
                if (!isTileEmpty(i, j)) {
                    setTileAt(i, count, getTileValue(i, j));
                    if (count > j) {
                        removeTileAt(i, j);
                    }
                    count--;
                }
            }
        }
    }

    public void mergeTilesDown() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = boardSize - 1; j > 0; j--) {
                if (getTileValue(i, j) == getTileValue(i, j - 1)) {
                    setTileAt(i, j, getTileValue(i, j) * 2);
                    setTileAt(i, j - 1, 0);
                }
            }
        }
    }

    public void shiftTilesLeft() {
        int count;
        for (int j = 0; j < boardSize; j++) {
            count = 0;
            for (int i = 0; i < boardSize; i++) {
                if (!isTileEmpty(i, j)) {
                    setTileAt(count, j, getTileValue(i, j));
                    if (count < i) {
                        removeTileAt(i, j);
                    }
                    count++;
                }
            }
        }
    }


    public void mergeTilesLeft() {
        for (int j = 0; j < boardSize; j++) {
            for (int i = 0; i < boardSize - 1; i++) {
                if (getTileValue(i, j) == getTileValue(i + 1, j)) {
                    setTileAt(i, j, getTileValue(i, j) * 2);
                    setTileAt(i + 1, j, 0);
                }
            }
        }
    }

    public void shiftTilesRight() {
        int count;
        for (int j = 0; j < boardSize; j++) {
            count = boardSize - 1;
            for (int i = boardSize - 1; i >= 0; i--) {
                if (!isTileEmpty(i, j)) {
                    setTileAt(count, j, getTileValue(i, j));
                    if (count > i) {
                        removeTileAt(i, j);
                    }
                    count--;
                }
            }
        }
    }


    public void mergeTilesRight() {
        for (int j = 0; j < boardSize; j++) {
            for (int i = boardSize - 1; i > 0; i--) {
                if (getTileValue(i, j) == getTileValue(i - 1, j)) {
                    setTileAt(i, j, getTileValue(i, j) * 2);
                    setTileAt(i - 1, j, 0);
                }
            }
        }
    }

    public void moveLeft() {
        shiftTilesLeft();
        mergeTilesLeft();
        shiftTilesLeft();
        insertRandomTile();
        addBoardToHistory();
    }

    public void moveRight() {
        shiftTilesRight();
        mergeTilesRight();
        shiftTilesRight();
        insertRandomTile();
        addBoardToHistory();
    }

    public void moveUp() {
        shiftTilesUp();
        mergeTilesUp();
        shiftTilesUp();
        insertRandomTile();
        addBoardToHistory();
    }

    public void moveDown() {
        shiftTilesDown();
        mergeTilesDown();
        shiftTilesDown();
        insertRandomTile();
        addBoardToHistory();
    }

    public void addBoardToHistory() {
        // deep clone the 2 dimensional tile lists
        List<List<TileSchema>> tilesClone = this.tiles.stream().map(ArrayList::new).collect(Collectors.toList());

        Game.HISTORY_CONTROLLER.addBoard(new BoardSchema(tilesClone, 4));
    }

    public int getBoardSize() {
        return boardSize;
    }

}
