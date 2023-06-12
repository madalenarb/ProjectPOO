package event;

import java.util.PriorityQueue;

import main.ParameterReader;

public class EventManager{
	private static EventManager managerInstance;
    private PriorityQueue<Event> pec;
    private double currentTime;
    private int moveEventCounter;
    private int pheromoneEventCounter;
    private int printEventCounter;

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
        this.printEventCounter = 0;
    }
    
    public int getMoveEventCounter() {
        return moveEventCounter;
    }

    public int getPheromoneEventCounter() {
        return pheromoneEventCounter;
    }
    
    public int getPrintEventCounter() {
        return printEventCounter;
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
        return pec.poll();
    }
    
    public void initializePEC() {
    	for(int i=0; i < ParameterReader.getNu(); i++) {
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
    
    public void incrementPrintEvent() {
        this.printEventCounter++;
    }
    
    public void run() {
        while (!pec.isEmpty() && currentTime <= ParameterReader.getTau()) {
            Event event = getEvent();
            event.executeEvent();
        }
        // System.out.println("Simulation ended at time " + currentTime);
    }
}
