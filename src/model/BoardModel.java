package model;

import java.util.List;

public class BoardModel {
    public List<List<TileModel>> tiles;
    public int boardSize;
    public long score;
    public long time;


    public BoardModel() {
    }

    public BoardModel(List<List<TileModel>> tiles, int boardSize, long score, long time) {
        this.tiles = tiles;
        this.boardSize = boardSize;
        this.score = score;
        this.time = time;
    }

    public List<List<TileModel>> getTiles() {
        return tiles;
    }

    public void setTiles(List<List<TileModel>> tiles) {
        this.tiles = tiles;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
