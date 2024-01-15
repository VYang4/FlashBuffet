package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event event;
    private Offering offering;
    private Event event2;
    private Event event3;
    private Date date;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("new offering added");
        offering = new Offering(1,1,"1", "1", "1");
        event2 = null;
        event3 = new Event("new offering");
        date = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("new offering added", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "new offering added", event.toString());
    }

    @Test
    public void testHashCode() {
        assertEquals(13 * event.getDate().hashCode() + event.getDescription().hashCode(),
                event.hashCode());
    }

    @Test
    public void testEquals() {
        assertFalse(event.equals(offering));
        assertFalse(event.equals(event2));
        assertTrue(event.equals(event));
        assertFalse(event.equals(event3));
    }
}
