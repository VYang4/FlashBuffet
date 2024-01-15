package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// CITATION: based on Event in AlarmSystem (https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git)
// Represents an event log
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    private EventLog() {
        events = new ArrayList<Event>();
    }

    // EFFECTS: returns the instance of EventLog; creates it if it does not already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the eventLog
    public void logEvent(Event e) {
        events.add(e);
    }

    // MODIFIES: this
    // EFFECTS: clears the eventLog and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
