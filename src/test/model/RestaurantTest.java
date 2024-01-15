package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RestaurantTest {
    private Offering testOffering1;
    private Offering testOffering2;
    private Restaurant testRestaurant1;
    private Restaurant testRestaurant2;

    @BeforeEach
    void runBefore() {
        testOffering1 = new Offering(1,15, "14:00 - 16:00", "1212 Broadway 2nd floor", "Chinese");
        testOffering2 = new Offering(2,20, "20:00 - 21:00", "1212 Broadway 2nd floor", "Mexican");
        testRestaurant1 = new Restaurant("Chipotle", "0100");
        testRestaurant2 = new Restaurant("Steak house","4004");
    }

    @Test
    void testConstructor() {
        assertEquals("Chipotle", testRestaurant1.getName());
        assertEquals(0, testRestaurant1.getListOfOfferings().size());
    }

    @Test
    void testAddOffering() {
        testRestaurant1.addOffering(testOffering1);
        testRestaurant1.addOffering(testOffering2);
        assertEquals(testOffering1,testRestaurant1.getOffering(1));
        assertEquals(2, testRestaurant1.getListOfOfferings().size());
    }

    @Test
    void testGetOffering() {
        assertNull(testRestaurant2.getOffering(2));
        testRestaurant2.addOffering(testOffering2);
        assertEquals(testOffering2,testRestaurant2.getOffering(2));
        assertNull(testRestaurant2.getOffering(4));
    }

    @Test
    void testRemoveOffering() {
        testRestaurant1.addOffering(testOffering1);
        testRestaurant1.addOffering(testOffering2);
        testRestaurant1.removeOffering(testOffering1);
        assertEquals(1, testRestaurant1.getListOfOfferings().size());
        assertEquals(testOffering2, testRestaurant1.getListOfOfferings().get(0));
    }

    @Test
    void testSetName() {
        assertEquals("Chipotle", testRestaurant1.getName());
        testRestaurant1.setName("5 guys");
        assertEquals("5 guys", testRestaurant1.getName());
    }

    @Test
    void testSetContactInfo() {
        assertEquals("4004", testRestaurant2.getContactInfo());
        testRestaurant2.setContactInfo("444");
        assertEquals("444", testRestaurant2.getContactInfo());
    }

    @Test
    void testGetOfferingDetails() {
        testRestaurant1.addOffering(testOffering1);
        testRestaurant1.addOffering(testOffering2);
        assertEquals(2, testRestaurant1.getOfferingDetails().size());
    }
}
