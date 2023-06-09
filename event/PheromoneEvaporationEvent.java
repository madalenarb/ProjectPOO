package event;

import main.ParameterReader;

import java.util.Random;

import graph.GraphFacade;

public class PheromoneEvaporationEvent implements Event{
    private double time;
    private int startNode;
    private int endNode;

    public PheromoneEvaporationEvent(double time, int startNode, int endNode) {
        this.time = time;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    @Override
    public void executeEvent(EventManager PEC) {
    	PEC.setTime(time);
    	boolean biggerThanZero = GraphFacade.getInstance().reducePheromones(startNode, endNode);
    	if(biggerThanZero) {
    		// Schedule another evaporation event
            PEC.incrementPheromoneEvent();
    		PEC.addEvent(new PheromoneEvaporationEvent(setNextEventTime(), startNode, endNode));
    	}
    }

    @Override
    public double getEventTime() {
        return time;
    }

    @Override
    public double setNextEventTime(){
        Random random = new Random();
        double next = random.nextDouble();
        double newEventTime = time + (-ParameterReader.getEta()*Math.log(1.0-next));
        return newEventTime;
    }
}
