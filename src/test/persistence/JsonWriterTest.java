package persistence;

import model.Offering;
import model.Restaurant;
import model.Restaurants;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Adapted from JsonSerializationDemo
public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            Restaurants rs = new Restaurants();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRestaurants() {
        try {
            Restaurants rs = new Restaurants();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRestaurants.json");
            writer.open();
            writer.write(rs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRestaurants.json");
            rs = reader.read();
            assertEquals(0, rs.getLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRestaurants() {
        try {
            Restaurants rs = new Restaurants();
            Restaurant r1 = new Restaurant("DoubleTree", "32423423");
            Restaurant r2 = new Restaurant("Walton", "00000000");
            Offering o1 = new Offering(1, 20, "14:00-16:00", "1st floor - 4400 Marriott Dr.",
                    "Thai");
            Offering o2 = new Offering(2, 35, "20:00-21:00", "2nd floor - 343 Pineapple St.",
                    "Barbecue");

            rs.addRestaurant(r1);
            rs.addRestaurant(r2);
            r1.addOffering(o1);
            r1.addOffering(o2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRestaurants.json");
            writer.open();
            writer.write(rs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRestaurants.json");
            rs = reader.read();
            assertEquals(2, rs.getLength());

            checkRestaurant("DoubleTree","32423423", r1);
            checkRestaurant("Walton","00000000", r2);

            checkOffering(1, 20, "14:00-16:00", "1st floor - 4400 Marriott Dr.",
                    "Thai", r1.getOffering(1));
            checkOffering(2, 35, "20:00-21:00", "2nd floor - 343 Pineapple St.",
                    "Barbecue", r1.getOffering(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
