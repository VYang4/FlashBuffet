package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Restaurants implements Writable {
    private List<Restaurant> listOfRestaurants;

    // EFFECTS: constructs a list of restaurants
    public Restaurants() {
        this.listOfRestaurants = new ArrayList<>();
    }

    // REQUIRES: restaurants does not already contain the same restaurant
    // MODIFIES: this
    // EFFECTS: adds a restaurant to the list
    public void addRestaurant(Restaurant restaurant) {
        listOfRestaurants.add(restaurant);
        EventLog.getInstance().logEvent(new Event("Added restaurant: " + restaurant.getName()));
    }

    // MODIFIES: this
    // EFFECTS: removes a restaurant from the list
    public void removeRestaurant(Restaurant restaurant) {
        listOfRestaurants.remove(restaurant);
        EventLog.getInstance().logEvent(new Event("Removed restaurant: " + restaurant.getName()));
    }

    // EFFECTS: returns the total number of a vendor's current restaurants
    public int getNumOfRestaurant() {
        return listOfRestaurants.size();
    }

    // EFFECTS: returns a list of all the offerings in a given restaurant;
    // else returns null
    public List<Offering> getListOfOfferings(String name) {
        List<Offering> result = null;
        for (Restaurant restaurant : listOfRestaurants) {
            if (restaurant.getName().equals(name)) {
                EventLog.getInstance().logEvent(new Event("Accessing restaurant: "
                        + restaurant.getName()));
                result = restaurant.getListOfOfferings();
                break;
            }
        }
        return result;
    }

    // EFFECTS: returns a restaurant by its name;
    // else returns null
    public Restaurant getRestaurant(String name) {
        Restaurant result = null;
        for (Restaurant restaurant : listOfRestaurants) {
            if (restaurant.getName().equals(name)) {
                result = restaurant;
                break;
            }
        }
        return result;
    }

    // EFFECTS: returns a restaurant by its name;
    // else returns null
    public List<String> getRestaurantNames() {
        List<String> listOfRestaurantNames = new ArrayList<String>();
        for (Restaurant restaurant : listOfRestaurants) {
            listOfRestaurantNames.add(restaurant.getName());
        }
        return listOfRestaurantNames;
    }

    // EFFECT: returns the number of restaurants in the list
    public int getLength() {
        return listOfRestaurants.size();
    }

    // Adapted from JSONSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("restaurants", restaurantsToJson());
        return json;
    }

    // Adapted from JSONSerializationDemo
    // EFFECTS: returns restaurants in this list as a JSON array
    private JSONArray restaurantsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Restaurant restaurant : listOfRestaurants) {
            jsonArray.put(restaurant.toJson());
        }

        EventLog.getInstance().logEvent(new Event("Restaurant saved"));
        return jsonArray;
    }
}
