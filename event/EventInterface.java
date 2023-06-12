package event;

/**
 * Represents an interface for events in the simulation.
 * Event classes implementing this interface should provide implementations
 * for the executeEvent(), getEventTime(), and setNextEventTime() methods.
 */
public interface EventInterface extends Comparable<EventInterface>{
    
    /**
     * Executes the event.
     */
    void executeEvent();
    
    /**
     * Gets the time of the event.
     *
     * @return The time of the event.
     */
    double getEventTime();

    /**
     * Sets the time for the next occurrence of the event.
     *
     * @return The time for the next occurrence of the event.
     */
    public double setNextEventTime();

    /**
     * Compares this event with the specified event for order.
     *
     * @param otherEvent The other event to compare.
     * @return A negative integer, zero, or a positive integer if this event's time
     *         is less than, equal to, or greater than the other event's time.
     */
    @Override
    default int compareTo(EventInterface otherEvent) {
        return Double.compare(this.getEventTime(), otherEvent.getEventTime());
    }
}