package event;

import java.util.Random;

import ant.AntColony;

/**
 * Represents an event where an ant moves to the next node in the graph.
 * 
 * <p>Class Parameters:</p>
 * <ul>
 * <li>{@code antID} - the ID of the ant that is moving.</li>
 * </ul>
 */
public class AntMoveEvent extends Event {
    private int antID;

    /**
     * Constructs a new AntMoveEvent with the specified time and ant ID.
     *
     * @param time   The time at which the event occurs.
     * @param antID  The ID of the ant.
     */
    public AntMoveEvent(double time, int antID) {
        super(time);
        this.antID = antID;
    }

    /**
     * Executes the event by moving the ant to the next node in the graph.
     * If the ant completes a cycle, pheromones are laid and the ant's path is restarted.
     */
    @Override
    public void executeEvent() {
        
    	AntColony antCol = AntColony.getInstance();
    	
        getPec().setTime(getEventTime());
        
        if (antCol.moveAnt(antID)) { // completed cycle
            // lay pheromones and create evaporation events for
            // the edges that didn't previously have pheromones
            antCol.antLaysPheromones(antID, getPec());
            // restart the ant's path
            antCol.restartPath(antID);
        }
        
        getPec().incrementMoveEvent();
        getPec().addEvent(new AntMoveEvent(setNextEventTime(), antID));
    }

    
    /**
     * Sets the time for the next AntMoveEvent based on the mean traverse time of the ant.
     *
     * @return The time for the next AntMoveEvent.
     */
    @Override
    public double setNextEventTime() {
    	
    	AntColony antCol = AntColony.getInstance();
    	
        Random random = new Random();
        double next = random.nextDouble();
        
        double newEventTime = getEventTime() + (-antCol.meanTraverseTime(antID) * Math.log(1.0 - next));
        return newEventTime;
    }
}