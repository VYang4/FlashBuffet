package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OfferingTest {
    private Offering testOffering;

    @BeforeEach
    void runBefore() {
        testOffering = new Offering(1,15, "14:00 - 16:00", "1212 Broadway 2nd floor", "Chinese");
    }

    @Test
    void testConstructor() {
        assertEquals(1, testOffering.getID());
        assertEquals(15, testOffering.getPrice());
        assertEquals("14:00 - 16:00", testOffering.getTime());
        assertEquals("1212 Broadway 2nd floor", testOffering.getLocation());
        assertEquals("Chinese", testOffering.getFoodType());
    }

    @Test
    void testSetID() {
        assertEquals(1, testOffering.getID());
        testOffering.setID(2);
        assertEquals(2, testOffering.getID());
    }

    @Test
    void testSetPrice() {
        assertEquals(15, testOffering.getPrice());
        testOffering.setPrice(20);
        assertEquals(20, testOffering.getPrice());
    }

    @Test
    void testSetTime() {
        assertEquals("14:00 - 16:00", testOffering.getTime());
        testOffering.setTime("15:00 - 17:00");
        assertEquals("15:00 - 17:00", testOffering.getTime());
    }

    @Test
    void testSetLocation() {
        assertEquals("1212 Broadway 2nd floor", testOffering.getLocation());
        testOffering.setLocation("1331 Broadway 1nd floor");
        assertEquals("1331 Broadway 1nd floor", testOffering.getLocation());
    }

    @Test
    void testSetFoodType() {
        assertEquals("Chinese", testOffering.getFoodType());
        testOffering.setType("Mexican");
        assertEquals("Mexican", testOffering.getFoodType());
    }
}