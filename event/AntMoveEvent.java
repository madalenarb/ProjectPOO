package event;

import java.util.Random;

import ant.AntColony;

public class AntMoveEvent extends Event {
    private int antID;
    private AntColony antCol;

    public AntMoveEvent(double time, int antID) {
        super(time);
        this.antID = antID;
        this.antCol = AntColony.getInstance();
    }

    @Override
    public void executeEvent() {
        if (antID == 0) {
            System.out.println("Ant " + antID + " moves at time " + getEventTime());
        }
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
        Random random = new Random();
        double next = random.nextDouble();
        System.out.println("Ant " + antID + " moves at time " + getEventTime() + " with next " + next);
        double newEventTime = getPec().getTime() + (-antCol.meanTraverseTime(antID) * Math.log(1.0 - next));
        return newEventTime;
    }
}
