package EventManager;

import java.util.PriorityQueue;
import main.ParameterReader;

public class EventManager{
    private final PriorityQueue<Event> pec;
    private double currentTime;

    public EventManager() {
        this.pec = new PriorityQueue<>();
        this.currentTime = 0;
    }

    public void addEvent(Event event) {
        if (event.getEventTime() <= ParameterReader.getTau()) {
            this.pec.add(event);
        }
    }

    public Event getEvent(){
        return this.pec.poll();
    }
    public void run() {
        while (!pec.isEmpty() && currentTime <= ParameterReader.getTau()) {
            Event event = pec.poll();
            currentTime = event.getEventTime();
            event.executeEvent();
        }
    }
}