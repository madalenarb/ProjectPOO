package event;

import java.util.PriorityQueue;

import main.ParameterReader;

public class EventManager{
	private static EventManager managerInstance;
    private PriorityQueue<Event> pec;
    private double currentTime;
    private int moveEventCounter;
    private int pheromoneEventCounter;

    public static EventManager getInstance() {
        if (managerInstance == null) {
        	synchronized (EventManager.class) {
                if (managerInstance == null) {
                	managerInstance = new EventManager();
                }
        	}
        }
        return managerInstance;
    }

    public EventManager() {
        this.pec = new PriorityQueue<>();
        this.currentTime = 0;
        this.moveEventCounter = 0;
        this.pheromoneEventCounter = 0;
    }
    
    public int getMoveEventCounter() {
        return moveEventCounter;
    }

    public int getPheromoneEventCounter() {
        return pheromoneEventCounter;
    }

    public void setTime(double time) {
    	currentTime = time;
    }
    
    public double getTime() {
    	 return currentTime;
    }

    public void addEvent(Event event) {
        if (event.getEventTime() <= ParameterReader.getTau()) {
            this.pec.add(event);
        }
    }

    public Event getEvent(){
        return this.pec.poll();
    }
    
    public void initializePEC() {
    	for(int i=0; i < ParameterReader.getN(); i++) {
    		addEvent(new AntMoveEvent(currentTime, i));
    	}
        addEvent(new PrintEvent(ParameterReader.getTau()/20));
    }

    public void incrementMoveEvent() {
        this.moveEventCounter++;
    }


    public void incrementPheromoneEvent() {
        this.pheromoneEventCounter++;
    }
    
    public void run() {
        while (!pec.isEmpty() && currentTime <= ParameterReader.getTau()) {
            Event event = pec.poll();
            event.executeEvent();
        }
        System.out.println("Simulation ended at time " + currentTime);
    }
}
