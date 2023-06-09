package event;

public interface EventInterface extends Comparable<EventInterface>{
    
    void executeEvent();
    double getEventTime();
    public double setNextEventTime();

    @Override
    default int compareTo(EventInterface otherEvent) {
        return Double.compare(this.getEventTime(), otherEvent.getEventTime());
    }
}