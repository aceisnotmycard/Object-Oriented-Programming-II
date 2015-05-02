package ru.nsu.ccfit.bogolepov.tetris.event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by aceisnotmycard on 5/2/15.
 */
public class EventQueue {

    private Queue<Event> eventQueue;

    public EventQueue() {
        eventQueue = new ConcurrentLinkedQueue<>();
    }

    public void addEvent(Event event) {
        eventQueue.add(event);
    }

    public boolean hasEvent() {
        return !eventQueue.isEmpty();
    }

    public Event getEvent() {
        return eventQueue.poll();
    }
}
