package persistence;

import model.Offering;
import model.Restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRestaurant(String name, String contactInfo, Restaurant restaurant) {
        assertEquals(name, restaurant.getName());
    }

    protected void checkOffering(int id, int price, String time, String location, String foodType, Offering offering) {
        assertEquals(id, offering.getID());
        assertEquals(price, offering.getPrice());
        assertEquals(time, offering.getTime());
        assertEquals(location, offering.getLocation());
        assertEquals(foodType, offering.getFoodType());
    }
}
