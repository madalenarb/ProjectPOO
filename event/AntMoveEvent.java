package event;

import java.util.Random;

import ant.AntColony;

public class AntMoveEvent implements Event{
    private double time;
    private int antID;
    private AntColony antCol;

    public AntMoveEvent(double time, int antID) {
        this.time = time;
        this.antID = antID;
        this.antCol = AntColony.getInstance();
    }

    @Override
    public void executeEvent(EventManager PEC) {
        if(antID == 0){
            System.out.println("Ant " + antID + " moves at time " + time);
        }
    	PEC.setTime(time);
        if(antCol.moveAnt(antID)) { // completed cycle
        	// lay pheromones and create evaporation events for
        	// the edges that didn't previously have pheromones
        	antCol.antLaysPheromones(antID, PEC);
        	// restart the ant's path
        	antCol.restartPath(antID);
        }
        PEC.addEvent(new AntMoveEvent(setNextEventTime(), antID));
    }

    @Override
    public double getEventTime() {
        return time;
    }
    
    @Override
    public double setNextEventTime(){
        Random random = new Random();
        double next = random.nextDouble();
        double newEventTime = time + (-antCol.meanTraverseTime(antID)*Math.log(1.0-next));
        return newEventTime;
    }
}
