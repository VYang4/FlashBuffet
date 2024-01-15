package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a list of Offerings and a few commands that can be done by the vendor
public class Restaurant implements Writable {
    private String name;
    private String contactInfo;
    private List<Offering> listOfOfferings;

    // EFFECTS: constructs a new ArrayList of Offerings in a restaurant
    public Restaurant(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.listOfOfferings = new ArrayList<>();
    }

    // REQUIRES: list does not already contain the same offering
    // MODIFIES: this
    // EFFECTS: adds an offering to the list for a given restaurant
    public void addOffering(Offering offering) {
        listOfOfferings.add(offering);
        EventLog.getInstance().logEvent(new Event("Added Offering: " + offering.getID() + "  |  "
                + offering.getPrice() + "  |  " + offering.getTime() + "  |  "
                + offering.getLocation() + "  |  " + offering.getFoodType()));
    }

    // MODIFIES: this
    // EFFECTS: removes an offering from the list
    public void removeOffering(Offering offering) {
        listOfOfferings.remove(offering);
        EventLog.getInstance().logEvent(new Event("Removed Option: " + offering.getID()));
    }

    // EFFECTS: returns the name of the given restaurant
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the contact info of the given restaurant
    public String getContactInfo() {
        return this.contactInfo;
    }

    // EFFECTS: returns a list of offerings of a given restaurant
    public List<Offering> getListOfOfferings() {
        return this.listOfOfferings;
    }

    // EFFECTS: returns an offering by its ID;
    // else returns null
    public Offering getOffering(int id) {
        Offering result = null;
        for (Offering offering : listOfOfferings) {
            if (offering.getID() == id) {
                result = offering;
                break;
            }
        }
        return result;
    }

    // MODIFIES：this
    // EFFECTS: set the name for restaurant
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES：this
    // EFFECTS: set the contact info for restaurant
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Adapted from JSONSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("contactInfo", contactInfo);
        json.put("offerings", offeringsToJson());
        return json;
    }

    // Adapted from JSONSerializationDemo
    // EFFECTS: returns recipes in this recipeList as a JSON array
    private JSONArray offeringsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Offering offering : listOfOfferings) {
            jsonArray.put(offering.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns a list of offerings in string;
    public List<String> getOfferingDetails() {
        List<String> listOfOfferings = new ArrayList<String>();
        List<Offering> offerings = this.getListOfOfferings();

        for (Offering offering: offerings) {
            String list = "Offering ID:" + offering.getID() + "\n"
                    + "Price: $" + offering.getPrice() + "\n"
                    + "Time: " + offering.getTime() + "\n"
                    + "Location: " + offering.getLocation() + "\n"
                    + "Food type: " + offering.getFoodType() + "\n" + "\n";
            listOfOfferings.add(list);
        }
        return listOfOfferings;

    }
}
