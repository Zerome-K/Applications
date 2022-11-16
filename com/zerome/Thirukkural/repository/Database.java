package thirukkural.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import thirukkural.model.Kural;
import thirukkural.model.Root;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Database {

    private static Database database = null;

    private Database(){
        jsonExecution();
    }

    public static Database getInstance(){
        if(database == null)database = new Database();
        return database;
    }
    private  ArrayList<Kural> kural;

    public ArrayList<Kural> getKural() {
        return kural;
    }

    private void jsonExecution() {
        ObjectMapper mapper = new ObjectMapper();
        String path = "C:\\Users\\Zerome\\IdeaProjects\\MiniProjects\\src" +
                "\\thirukkural\\repository\\thirukkural.json";
        try {
            Root root = mapper.readValue(new File(path), Root.class);
            kural = root.getKural();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
