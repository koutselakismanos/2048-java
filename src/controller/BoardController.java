package controller;

import model.BoardModel;
import model.TileModel;
import utilities.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BoardController extends BoardModel {
    private final int delay = 1000;
    private Timer timer;

    public BoardController(int boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * Restart timer, and re-generate board
     */
    public void resetBoard() {
        if (timer != null) {
            timer.stop();
        }

        resetHistory();
        time = 0;

        ActionListener taskPerformer = evt -> {
            time += delay;
            Game.WINDOW.repaint();
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();

        score = 0;
        generateBoard();

        // insert 2 random tiles
        insertRandomTile();
        insertRandomTile();
        addBoardToHistory();
        Game.WINDOW.repaint();
    }

    public void stopGame() {
        timer.stop();
        Game.DATABASE.storeGame(Game.HISTORY_CONTROLLER);
    }

    /**
     * Generate board
     */
    public void generateBoard() {
        tiles = new ArrayList<>();

        for (int i = 0; i < boardSize; i++) {
            tiles.add(new ArrayList<>());
            for (int j = 0; j < boardSize; j++) {
                tiles.get(i).add(j, null);
            }
        }
    }

    public void setBoard(BoardModel boardModel) {
        this.tiles = boardModel.tiles;
        this.time = boardModel.time;
        this.score = boardModel.score;
        this.boardSize = boardModel.boardSize;
    }

    /**
     * Insert a random tile with a value of 2 with percentage 90% and a value of 4 with percentage of 10%
     */
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

    /**
     * Sets a tile at x and y location with a value
     */
    public void setTileAt(int x, int y, int value) {
        if (x >= boardSize || y >= boardSize) {
            return;
        }

        if (value == 0) {
            tiles.get(x).set(y, null);
            return;
        }

        tiles.get(x).set(y, new TileModel(x, y, value));
    }

    public TileModel getTileAt(int x, int y) {
        return tiles.get(x).get(y);
    }

    public void removeTileAt(int x, int y) {
        tiles.get(x).set(y, null);
    }

    /**
     * Check if tile is not null at x, y location
     */
    public boolean isTileNotEmpty(int x, int y) {
        return tiles.get(x).get(y) != null;
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
                if (isTileNotEmpty(i, j)) {
                    setTileAt(i, count, getTileValue(i, j));
                    if (count < j) {
                        removeTileAt(i, j);
                    }
                    count++;
                }
            }
        }
    }

    /**
     * Merge up all tiles that are next to each other with the same value
     */
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
                if (isTileNotEmpty(i, j)) {
                    setTileAt(i, count, getTileValue(i, j));
                    if (count > j) {
                        removeTileAt(i, j);
                    }
                    count--;
                }
            }
        }
    }

    /**
     * Merge down all tiles that are next to each other with the same value
     */
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
                if (isTileNotEmpty(i, j)) {
                    setTileAt(count, j, getTileValue(i, j));
                    if (count < i) {
                        removeTileAt(i, j);
                    }
                    count++;
                }
            }
        }
    }


    /**
     * Merge left all tiles that are next to each other with the same value
     */
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
                if (isTileNotEmpty(i, j)) {
                    setTileAt(count, j, getTileValue(i, j));
                    if (count > i) {
                        removeTileAt(i, j);
                    }
                    count--;
                }
            }
        }
    }

    /**
     * Merge right all tiles that are next to each other with the same value
     */
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
        calculateScore();
        addBoardToHistory();
    }

    public void moveRight() {
        shiftTilesRight();
        mergeTilesRight();
        shiftTilesRight();
        insertRandomTile();
        calculateScore();
        addBoardToHistory();
    }

    public void moveUp() {
        shiftTilesUp();
        mergeTilesUp();
        shiftTilesUp();
        insertRandomTile();
        calculateScore();
        addBoardToHistory();
    }

    public void moveDown() {
        shiftTilesDown();
        mergeTilesDown();
        shiftTilesDown();
        insertRandomTile();
        calculateScore();
        addBoardToHistory();
    }

    /**
     * Adds the current board to the history controller
     */
    public void addBoardToHistory() {
        // deep clone the 2 dimensional tile lists
        List<List<TileModel>> tilesClone = this.tiles.stream().map(ArrayList::new).collect(Collectors.toList());

        Game.HISTORY_CONTROLLER.addBoard(new BoardModel(tilesClone, boardSize, score, time));
    }

    /**
     * Resets history controller
     */
    public void resetHistory() {
        Game.HISTORY_CONTROLLER.resetHistory();
    }

    /**
     * Calculate score by summing up all tile values
     */
    public void calculateScore() {
        int sum = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (isTileNotEmpty(i, j)) {
                    sum += tiles.get(i).get(j).getValue();
                }
            }
        }

        score = sum;
    }

    public long getTimer() {
        return time;
    }

    public long getScore() {
        return score;
    }
}
