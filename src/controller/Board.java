package controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    List<List<Tile>> tiles;

    int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        generateBoard();

        insertRandomTile();
        insertRandomTile();
//        setTileAt(0, 0, 2);
//        setTileAt(0, 1, 2);
//        setTileAt(0, 2, 2);
//        setTileAt(0, 3, 2);
//
//        setTileAt(0, 5, 2);
//        setTileAt(0, 6, 2);
//        setTileAt(0, 7, 2);
//        setTileAt(0, 8, 2);
        printBoard();
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
        int randomNumber = rd.nextInt(emptyTiles.size());

        Point newTilePoint = emptyTiles.get(randomNumber);

        int twoOrFour;
        twoOrFour = rd.nextBoolean() ? 2 : 4;

        setTileAt(newTilePoint.x, newTilePoint.y, twoOrFour);
    }

    public void setTileAt(int x, int y, int value) {
        if (x >= boardSize || y >= boardSize) {
            return;
        }

        if (value == 0) {
            tiles.get(x).set(y, null);
            return;
        }

        tiles.get(x).set(y, new Tile(x, y, value));
    }

    public Tile getTileAt(int x, int y) {
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
    }

    public void moveRight() {
        shiftTilesRight();
        mergeTilesRight();
        shiftTilesRight();
    }

    public void moveUp() {
        shiftTilesUp();
        mergeTilesUp();
        shiftTilesUp();
    }

    public void moveDown() {
        shiftTilesDown();
        mergeTilesDown();
        shiftTilesDown();
    }

    public int getBoardSize() {
        return boardSize;
    }

}
