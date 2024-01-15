package ui;

import model.Offering;
import model.Restaurant;
import model.Restaurants;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Adapted from TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Surplus buffet listing application
public class ListingApp {
    private static final String JSON_STORE = "./data/listings.json";
    private Offering offering1;
    private Offering offering2;
    private Offering offering3;
    private Offering offering4;
    private Restaurant restaurant1;
    private Restaurant restaurant2;
    private Restaurants restaurants;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Adapted from TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
    // EFFECTS: runs the listing application
    public ListingApp() throws FileNotFoundException {
        restaurants = new Restaurants();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runListingApp();
    }

    // Adapted from TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runListingApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            //command = command.toLowerCase();

            if (command.equals("0")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you later!");
    }

    // Adapted from TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
    // MODIFIES: this
    // EFFECTS: initializes ListingApp
    private void init() {
        offering1 = new Offering(1,10,"14:00 - 15:00","2nd floor, 3337 W 45th Ave","Chinese");
        offering2 = new Offering(2,20,"20:00 - 21:00","3nd floor, 3337 W 45th Ave","Thai");
        offering3 = new Offering(1,25,"14:00 - 15:00","2nd floor, 44 W 10th Ave","Mexican");
        offering4 = new Offering(2,20,"20:00 - 21:00","1nd floor, 44 W 10th Ave","Indian");

        restaurant1 = new Restaurant("Hyatt","Front desk - 73700000");
        restaurant1.addOffering(offering1);
        restaurant1.addOffering(offering2);
        restaurant2 = new Restaurant("Hilton","Front desk - 74700000");
        restaurant2.addOffering(offering3);
        restaurant2.addOffering(offering4);

        restaurants = new Restaurants();
        restaurants.addRestaurant(restaurant1);
        restaurants.addRestaurant(restaurant2);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // Adapted from TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> search restaurant");
        System.out.println("\t2 -> add a new offering");
        System.out.println("\t3 -> delete an offering");
        System.out.println("\t4 -> add a new restaurant");
        System.out.println("\t5 -> delete a restaurant");
        System.out.println("\t6 -> display all restaurants");
        System.out.println("\t7 -> save restaurants to file");
        System.out.println("\t8 -> load restaurants from file");
        System.out.println("\t0 -> quit");
    }

    // Adapted from TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            searchRestaurant();
        } else if (command.equals("2")) {
            listOffering();
        } else if (command.equals("3")) {
            removeOffering();
        } else if (command.equals("4")) {
            listRestaurant();
        } else if (command.equals("5")) {
            removeRestaurant();
        } else if (command.equals("6")) {
            displayRestaurants();
        } else if (command.equals("7")) {
            saveRestaurants();
        } else if (command.equals("8")) {
            loadRestaurants();
        } else {
            System.out.println("Invalid selection - Please try another key.");
        }
    }

    // REQUIRES: list of offerings is not empty
    // EFFECTS: display list of offerings for a given restaurant
    private void searchRestaurant() {
        System.out.println("Which restaurant?");
        String name = input.next();
        List<Offering> offerings = restaurants.getListOfOfferings(name);
        String listOfOfferings = "";
        for (Offering offering: offerings) {
            listOfOfferings += "Offering ID:" + offering.getID() + "\n"
                    + "Price: $" + offering.getPrice() + "\n"
                    + "Time: " + offering.getTime() + "\n"
                    + "Location: " + offering.getLocation() + "\n"
                    + "Food type: " + offering.getFoodType() + "\n" + "\n";
        }
        System.out.println(listOfOfferings);
    }

    // REQUIRES: offering is not already in the list
    // EFFECTS: adds an offering to the restaurant
    private void listOffering() {
        Offering newListing = new Offering(0,0,"","","");
        System.out.println("ID?");
        int id = input.nextInt();
        newListing.setID(id);
        System.out.println("How much?");
        int price = input.nextInt();
        newListing.setPrice(price);
        System.out.println("What time?");
        String time = input.next();
        newListing.setTime(time);
        System.out.println("Where?");
        String location = input.next();
        newListing.setLocation(location);
        System.out.println("What type?");
        String foodType = input.next();
        newListing.setType(foodType);

        System.out.println("For which restaurant? Please enter restaurant name");
        String name = input.next();
        restaurants.getRestaurant(name).addOffering(newListing);
        System.out.println("Your new offering has been successfully added!");
    }

    // REQUIRES: offering is already in the list
    // EFFECTS: removes an offering from the restaurant
    private void removeOffering() {
        System.out.println("For which restaurant? Please enter restaurant name");
        String name = input.next();
        System.out.println("Which listing？ Please enter offering ID");
        int id = input.nextInt();
        Offering offering = restaurants.getRestaurant(name).getOffering(id);
        restaurants.getRestaurant(name).removeOffering(offering);
        System.out.println("Your listing has been successfully removed!");
    }

    // REQUIRES: restaurant is not already in the list
    // EFFECTS: adds a restaurant to the restaurant
    private void listRestaurant() {
        Restaurant newRestaurant = new Restaurant("","");
        System.out.println("Name?");
        String name = input.next();
        newRestaurant.setName(name);
        System.out.println("Contact info?");
        String contact = input.next();
        newRestaurant.setContactInfo(contact);

        restaurants.addRestaurant(newRestaurant);
        System.out.println("Your new restaurant has been successfully added!");
    }

    // REQUIRES: restaurant is already in the list
    // EFFECTS: removes a restaurant from the restaurant
    private void removeRestaurant() {
        System.out.println("Which restaurant? Please enter restaurant name");
        String name = input.next();
        Restaurant restaurant = restaurants.getRestaurant(name);
        restaurants.removeRestaurant(restaurant);
        System.out.println("Your restaurant has been successfully removed!");
    }

    // EFFECTS: display all restaurants
    private void displayRestaurants() {
        System.out.println("\n" + restaurants.getRestaurantNames());
    }

    // EFFECTS: saves the restaurants to file
    private void saveRestaurants() {
        try {
            jsonWriter.open();
            jsonWriter.write(restaurants);
            jsonWriter.close();
            System.out.println("Saved restaurants to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the restaurants from file
    private void loadRestaurants() {
        try {
            restaurants = jsonReader.read();
            System.out.println("Loaded restaurants from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

