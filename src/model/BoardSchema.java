package model;

import java.util.List;

public class BoardSchema {
    public List<List<TileSchema>> tiles;
    public int boardSize;

    public BoardSchema() {
    }

    public BoardSchema(List<List<TileSchema>> tiles, int boardSize) {
        this.tiles = tiles;
        this.boardSize = boardSize;
    }

    public List<List<TileSchema>> getTiles() {
        return tiles;
    }

    public void setTiles(List<List<TileSchema>> tiles) {
        this.tiles = tiles;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

}
