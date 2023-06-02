package EventManager;

public interface Event extends Comparable<Event>{
    void executeEvent();
    double getEventTime();

    @Override
    default int compareTo(Event otherEvent) {
        return Double.compare(this.getEventTime(), otherEvent.getEventTime());
    }
}