package event;

import main.ParameterReader;

import java.util.Random;

import graph.GraphFacade;

public class PheromoneEvaporationEvent implements Event{
    private double time;
    private int startNode;
    private int endNode;

    public PheromoneEvaporationEvent(int startNode, int endNode) {
        this.time = setNextEventTime();
        this.startNode = startNode;
        this.endNode = endNode;
    }

    @Override
    public void executeEvent(EventManager PEC) {
    	PEC.setTime(time);
    	boolean biggerThanZero = GraphFacade.getInstance().reducePheromones(startNode, endNode);
    	if(biggerThanZero) {
    		// Schedule another evaporation event
    		PEC.addEvent(new PheromoneEvaporationEvent(startNode, endNode));
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
        double newEventTime = time + (-ParameterReader.getEta()*Math.log(1.0-next));//get PEC time
        return newEventTime;
    }
}
