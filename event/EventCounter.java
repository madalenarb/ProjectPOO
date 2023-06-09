package event;

public class EventCounter {
	private int mevents;
	private int eevents;
	private static EventCounter instance;
	
	public EventCounter() {
		mevents = 0;
		eevents = 0;
	}
	
	public static EventCounter getInstance() {
        if (instance == null) {
        	synchronized (EventCounter.class) {
                if (instance == null) {
                    instance = new EventCounter();
                }
        	}
        }
        return instance;
    }
	
	public void countEvents(Event event) {
		if(event instanceof AntMoveEvent) {
			++mevents;
		}
		else if(event instanceof PheromoneEvaporationEvent) {
			++eevents;
		}
	}
	
	public int getAntMoveEvents() {
		return mevents;
	}
	
	public int getPheromoneEvaporationEvents() {
		return eevents;
	}
}
