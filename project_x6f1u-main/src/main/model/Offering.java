package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an offering with price, time, location and type of food
public class Offering implements Writable {
    private int id;
    private int price;
    private String time;
    private String location;
    private String foodType;

    // REQUIRES: price >= 0, time >= 0
    // EFFECTS: constructs a new offering
    public Offering(int id, int price, String time, String location, String foodType) {
        this.id = id;
        this.price = price;
        this.time = time;
        this.location = location;
        this.foodType = foodType;
    }

    public int getID() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getFoodType() {
        return foodType;
    }

    // MODIFIES：this
    // EFFECTS: set the ID for offering
    public void setID(int id) {
        this.id = id;
    }

    // MODIFIES：this
    // EFFECTS: set the price for offering
    public void setPrice(int price) {
        this.price = price;
    }

    // MODIFIES：this
    // EFFECTS: set the time for offering
    public void setTime(String time) {
        this.time = time;
    }

    // MODIFIES：this
    // EFFECTS: set the location for offering
    public void setLocation(String location) {
        this.location = location;
    }

    // MODIFIES：this
    // EFFECTS: set the food type for offering
    public void setType(String foodType) {
        this.foodType = foodType;
    }

    // Adapted from JSONSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("price", price);
        json.put("time", time);
        json.put("location", location);
        json.put("foodType", foodType);
        return json;
    }
}
