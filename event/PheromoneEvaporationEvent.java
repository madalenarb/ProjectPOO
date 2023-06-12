package event;

import main.ParameterReader;

import java.util.Random;

import graph.GraphFacade;

/**
 * Represents a pheromone evaporation event in the simulation.
 * 
 * <p>Class Parameters:</p>
 * <ul>
 * <li>{@code startNode} - the starting node of the edge.</li>
 * <li>{@code endNode} - the ending node of the edge.</li>
 * </ul>
 */
public class PheromoneEvaporationEvent extends Event{
    private int startNode;
    private int endNode;

    /**
     * Constructs a new PheromoneEvaporationEvent object.
     *
     * @param time      The time at which the event occurs.
     * @param startNode The starting node of the edge.
     * @param endNode   The ending node of the edge.
     */
    public PheromoneEvaporationEvent(double time, int startNode, int endNode) {
        super(time);
        this.startNode = startNode;
        this.endNode = endNode;
    }

    /**
     * Executes the pheromone evaporation event.
     */
    @Override
    public void executeEvent() {
    	getPec().setTime(getEventTime());
    	boolean biggerThanZero = GraphFacade.getInstance().reducePheromones(startNode, endNode);
        getPec().incrementPheromoneEvent();
        // If there are still pheromones remaining
    	if(biggerThanZero) {
    		// Schedule another evaporation event
    		getPec().addEvent(new PheromoneEvaporationEvent(setNextEventTime(), startNode, endNode));
    	}
    }

    
    /**
     * Sets the time for the next pheromone evaporation event.
     *
     * @return The time for the next event.
     */
    @Override
    public double setNextEventTime(){
        Random random = new Random();
        double next = random.nextDouble();
        double newEventTime = getEventTime() + (-ParameterReader.getEta()*Math.log(1.0-next)); // Get PEC time
        return newEventTime;
    }
}
