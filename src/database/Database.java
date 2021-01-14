package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.GameFileModel;
import model.HistoryModel;
import utilities.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {
    private final Gson gson;
    private final String storageFolder = "src/data/";
    private static Database instance;

    private Database() {
        this.gson = new GsonBuilder()
                .serializeNulls()
                .create();
    }

    /**
     * Will store a game file in the format playerName_index.json e.g. manos_2.json
     *
     * @param object Any java object
     */
    public void storeGame(Object object) {
        int count = getCountOfUserGames();
        String path = storageFolder + Game.getPlayerName() + "_" + count + ".json";

        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(object, writer);
            System.out.println(gson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Load a game file
     *
     * @param index of game file
     * @return The game file data
     */
    public HistoryModel loadGame(int index) {
        String path = storageFolder + Game.getPlayerName() + "_" + index + ".json";
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, HistoryModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @return A list of game file models
     */
    public List<GameFileModel> getAllUserGames() {
        List<GameFileModel> gameFiles = new ArrayList<>();
        try {
            if (!Game.getPlayerName().equals("")) {
                File dir = new File(storageFolder);
                for (File file : Objects.requireNonNull(dir.listFiles())) {
                    if (file.getName().startsWith(Game.getPlayerName())) {
                        String index = file
                                .getName()
                                .substring(0, file.getName().length() - 5)
                                .split("_")[1];

                        gameFiles.add(new GameFileModel(file.getAbsolutePath(), Game.getPlayerName(), Integer.parseInt(index)));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return gameFiles;
    }

    /**
     * Get the total amount of game files per user
     */
    public int getCountOfUserGames() {
        File dir = new File(storageFolder);
        int count = 0;
        try {

            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.getName().startsWith(Game.getPlayerName())) {
                    count++;
                }
            }
        } catch (Exception e) {
            return 0;
        }

        return count;
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
            return instance;
        }

        return instance;
    }
}
