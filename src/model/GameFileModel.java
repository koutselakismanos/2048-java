package model;

public class GameFileModel {
    private String path;
    private String playerName;
    private int index;

    public GameFileModel(String path, String playerName, int index) {
        this.path = path;
        this.playerName = playerName;
        this.index = index;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
