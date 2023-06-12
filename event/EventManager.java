package event;

import java.util.PriorityQueue;

import main.ParameterReader;

/**
 * Represents the event manager for handling events in the simulation.
 * The event manager is a singleton class.
 * 
 * <p>Class Parameters:</p>
 * <ul>
 * <li>{@code managerInstance} - the instance of the EventManager.</li>
 * <li>{@code pec} - the priority queue of events.</li>
 * <li>{@code currentTime} - the current time of the simulation.</li>
 * <li>{@code moveEventCounter} - the number of move events that have occurred.</li>
 * <li>{@code pheromoneEventCounter} - the number of pheromone events that have occurred.</li>
 * <li>{@code printEventCounter} - the number of print events that have occurred.</li>
 * </ul>
 */
public class EventManager{
	private static EventManager managerInstance;
    private PriorityQueue<Event> pec;
    private double currentTime;
    private int moveEventCounter;
    private int pheromoneEventCounter;
    private int printEventCounter;

    
    /**
     * Gets the instance of the EventManager (Singleton pattern).
     *
     * @return The EventManager instance.
     */
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


    /**
     * Initializes a new instance of the EventManager class.
     */
    public EventManager() {
        this.pec = new PriorityQueue<>();
        this.currentTime = 0;
        this.moveEventCounter = 0;
        this.pheromoneEventCounter = 0;
        this.printEventCounter = 0;
    }
    
    /**
     * Gets the number of move events that have occurred.
     *
     * @return The number of move events.
     */
    public int getMoveEventCounter() {
        return moveEventCounter;
    }

    /**
     * Gets the number of pheromone events that have occurred.
     *
     * @return The number of pheromone events.
     */
    public int getPheromoneEventCounter() {
        return pheromoneEventCounter;
    }
    
    /**
     * Gets the number of print events that have occurred.
     *
     * @return The number of print events.
     */
    public int getPrintEventCounter() {
        return printEventCounter;
    }

    /**
     * Sets the current time in the event manager.
     *
     * @param time The current time.
     */
    public void setTime(double time) {
    	currentTime = time;
    }
    
    /**
     * Gets the current time from the event manager.
     *
     * @return The current time.
     */
    public double getTime() {
    	 return currentTime;
    }

    /**
     * Adds an event to the event manager.
     * The event is added only if its time is within the simulation time.
     *
     * @param event The event to add.
     */
    public void addEvent(Event event) {
        if (event.getEventTime() <= ParameterReader.getTau()) {
            this.pec.add(event);
        }
    }

    /**
     * Retrieves and removes the next event from the event manager.
     *
     * @return The next event.
     */
    public Event getEvent(){
        return pec.poll();
    }
    
    /**
     * Initializes the PEC with the initial events.
     */
    public void initializePEC() {
    	for(int i=0; i < ParameterReader.getNu(); i++) {
    		addEvent(new AntMoveEvent(currentTime, i));
    	}
        addEvent(new PrintEvent(ParameterReader.getTau()/20));
    }

    /**
     * Increments the move event counter.
     */
    public void incrementMoveEvent() {
        this.moveEventCounter++;
    }

    /**
     * Increments the pheromone event counter.
     */
    public void incrementPheromoneEvent() {
        this.pheromoneEventCounter++;
    }
    
    /**
     * Increments the print event counter.
     */
    public void incrementPrintEvent() {
        this.printEventCounter++;
    }
    
    /**
     * Runs the event manager and executes events until the simulation end time is reached.
     */
    public void run() {
        while (!pec.isEmpty() && currentTime <= ParameterReader.getTau()) {
            Event event = getEvent();
            event.executeEvent();
        }
    }
}
