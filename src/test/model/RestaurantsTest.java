package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RestaurantsTest {
    private Offering testOffering1;
    private Offering testOffering2;
    private Restaurant testRestaurant1;
    private Restaurant testRestaurant2;
    private Restaurants testRestaurants;

    @BeforeEach
    void runBefore() {
        testOffering1 = new Offering(1,15, "14:00 - 16:00", "1212 Broadway 2nd floor", "Chinese");
        testOffering2 = new Offering(2,20, "20:00 - 21:00", "1212 Broadway 2nd floor", "Mexican");
        testRestaurant1 = new Restaurant("Chipotle", "0100");
        testRestaurant2 = new Restaurant("Steak house","4004");
        testRestaurants = new Restaurants();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testRestaurants.getNumOfRestaurant());
    }

    @Test
    void testAddRestaurant() {
        testRestaurants.addRestaurant(testRestaurant1);
        testRestaurants.addRestaurant(testRestaurant2);
        assertEquals(2, testRestaurants.getNumOfRestaurant());
    }

    @Test
    void testRemoveRestaurant() {
        testRestaurants.addRestaurant(testRestaurant1);
        testRestaurants.addRestaurant(testRestaurant2);
        testRestaurants.removeRestaurant(testRestaurant1);
        assertEquals(1, testRestaurants.getNumOfRestaurant());
    }

    @Test
    void testGetListOfOfferings() {
        assertNull(testRestaurants.getListOfOfferings("Chipotle"));
        testRestaurant1.addOffering(testOffering1);
        testRestaurant1.addOffering(testOffering2);
        testRestaurant2.addOffering(testOffering1);
        testRestaurants.addRestaurant(testRestaurant1);
        testRestaurants.addRestaurant(testRestaurant2);
        assertEquals(2, testRestaurants.getListOfOfferings("Chipotle").size());
        assertEquals(1, testRestaurants.getListOfOfferings("Steak house").size());
        assertEquals(testOffering1, testRestaurants.getListOfOfferings("Chipotle").get(0));
        assertNull(testRestaurants.getListOfOfferings("KFC"));
    }

    @Test
    void testGetRestaurant() {
        assertNull(testRestaurants.getRestaurant("Chipotle"));
        testRestaurants.addRestaurant(testRestaurant1);
        testRestaurants.addRestaurant(testRestaurant2);
        assertEquals(testRestaurant1, testRestaurants.getRestaurant("Chipotle"));
        assertEquals(testRestaurant2, testRestaurants.getRestaurant("Steak house"));
        assertNull(testRestaurants.getRestaurant("KFC"));
    }

    @Test
    void testGetRestaurantNames() {
        testRestaurants.addRestaurant(testRestaurant1);
        testRestaurants.addRestaurant(testRestaurant2);
        assertEquals("Chipotle", testRestaurants.getRestaurantNames().get(0));
        assertEquals("Steak house", testRestaurants.getRestaurantNames().get(1));
        assertEquals(2, testRestaurants.getLength());
    }
}
