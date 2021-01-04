package controller;

public class Tile {
    private int positionX;
    private int positionY;
    private int value;

    /**
     * @param x     position
     * @param y     position
     * @param value of tile
     */
    public Tile(int x, int y, int value) {
        this.positionX = x;
        this.positionY = y;
        this.value = value;
    }

    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
