package event;

public interface Event extends Comparable<Event>{
    void executeEvent(EventManager PEC);
    double getEventTime();
    
    public double setNextEventTime();

    @Override
    default int compareTo(Event otherEvent) {
        return Double.compare(this.getEventTime(), otherEvent.getEventTime());
    }
}