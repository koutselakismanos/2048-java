package database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Database {
    private final Gson gson;
    private final String pathToDatabaseFile = "src/database.json";
    private static Database instance;

    private Database() {
        this.gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create();
    }

    public void store(Object object) {
        try (FileWriter writer = new FileWriter(pathToDatabaseFile)) {
            gson.toJson(object, writer);
            System.out.println(gson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
            return instance;
        }

        return instance;
    }

}
