package persistence;

import model.Offering;
import model.Restaurant;
import model.Restaurants;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Adapted from JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads restaurant from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Restaurants read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRestaurants(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses restaurant from JSON object and returns it
    private Restaurants parseRestaurants(JSONObject jsonObject) {
        Restaurants rs = new Restaurants();
        addRestaurants(rs, jsonObject);
        return rs;
    }

    // MODIFIES: rs
    // EFFECTS: parses list of restaurants from JSON object and adds them to restaurants
    private void addRestaurants(Restaurants rs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("restaurants");
        for (Object json : jsonArray) {
            JSONObject nextRestaurant = (JSONObject) json;
            addRestaurant(rs, nextRestaurant);
        }
    }

    // MODIFIES: r
    // EFFECTS: parses offerings from JSON object and adds them to restaurant
    private void addRestaurant(Restaurants rs, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String contactInfo = jsonObject.getString("contactInfo");
        Restaurant restaurant = new Restaurant(name, contactInfo);
        rs.addRestaurant(restaurant);
        JSONArray jsonArray = jsonObject.getJSONArray("offerings");
        for (Object json : jsonArray) {
            JSONObject nextOffering = (JSONObject) json;
            addListing(rs.getRestaurant(name), nextOffering);
        }
    }

    // MODIFIES: o
    // EFFECTS: parses offering from JSON object and adds it to restaurant
    private void addListing(Restaurant r, JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        int price = jsonObject.getInt("price");
        String time = jsonObject.getString("time");
        String location = jsonObject.getString("location");
        String foodType = jsonObject.getString("foodType");
        Offering offering = new Offering(id, price, time, location, foodType);

        r.addOffering(offering);
    }
}
