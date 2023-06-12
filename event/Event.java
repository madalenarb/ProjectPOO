package event;

/**
 * Represents an abstract Event in the simulation.
 * Subclasses of Event should implement the executeEvent() method.
 * 
 * <p>Class Parameters:</p>
 * <ul>
 * <li>{@code time} - the time at which the event occurs.</li>
 * <li>{@code pec} - the event manager for handling events.</li>
 * </ul>
 */
abstract class Event implements EventInterface{
    private double time;
    private EventManager pec;

    /**
     * Constructs a new Event with the specified time.
     *
     * @param time The time at which the event occurs.
     */
    public Event(double time){
        this.time = time;
        this.pec = EventManager.getInstance();
    }

    
    /**
     * Gets the time of the event.
     *
     * @return The time of the event.
     */
    public double getEventTime(){
        return this.time;
    }

    /**
     * Gets the current time of the EventManager.
     *
     * @return The current time of the EventManager.
     */
    public double getPECTime(){
        return this.pec.getTime();
    }

    /**
     * Gets the EventManager associated with this event.
     *
     * @return The EventManager associated with this event.
     */
    public EventManager getPec(){
        return this.pec;
    }
}