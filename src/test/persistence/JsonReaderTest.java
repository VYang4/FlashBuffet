package persistence;

import model.Restaurant;
import model.Restaurants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Adapted from JsonSerializationDemo
public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Restaurants rs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRestaurants() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRestaurants.json");
        try {
            Restaurants rs = reader.read();
            assertEquals(0, rs.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRestaurants() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRestaurants.json");
        try {
            Restaurants rs = reader.read();
            assertEquals(3, rs.getLength());
            assertEquals("Marriott", rs.getRestaurantNames().get(0));

            Restaurant restaurant1 = rs.getRestaurant("Marriott");
            Restaurant restaurant2 = rs.getRestaurant("Sheraton");
            Restaurant restaurant3 = rs.getRestaurant("Fairmont");

            checkRestaurant("Marriott","89809090", restaurant1);
            checkRestaurant("Sheraton","66779933", restaurant2);
            checkRestaurant("Fairmont","44558899", restaurant3);

            checkOffering(1, 20, "14:00-16:00", "1st floor - 4400 Marriott Dr.",
                    "Thai", restaurant1.getOffering(1));
            checkOffering(1, 35, "20:00-21:00", "2nd floor - 343 Pineapple St.",
                    "Barbecue", restaurant2.getOffering(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
