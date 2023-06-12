package event;

import java.util.Random;

import ant.AntColony;

public class AntMoveEvent extends Event {
    private int antID;

    public AntMoveEvent(double time, int antID) {
        super(time);
        this.antID = antID;
    }

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

    @Override
    public double setNextEventTime() {
    	
    	AntColony antCol = AntColony.getInstance();
    	
        Random random = new Random();
        double next = random.nextDouble();
        
        double newEventTime = getEventTime() + (-antCol.meanTraverseTime(antID) * Math.log(1.0 - next));
        return newEventTime;
    }
}