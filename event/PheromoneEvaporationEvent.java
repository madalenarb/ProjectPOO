package event;

import main.ParameterReader;

import java.util.Random;

import graph.GraphFacade;

public class PheromoneEvaporationEvent extends Event{
    private int startNode;
    private int endNode;

    public PheromoneEvaporationEvent(double time, int startNode, int endNode) {
        super(calculateEventTime(time));
        this.startNode = startNode;
        this.endNode = endNode;
    }

    @Override
    public void executeEvent() {
        System.out.println("Pheromone evaporation event at time " + getEventTime());
    	getPec().setTime(getEventTime());
    	boolean biggerThanZero = GraphFacade.getInstance().reducePheromones(startNode, endNode);
        getPec().incrementPheromoneEvent();
    	if(biggerThanZero) {
    		// Schedule another evaporation event
    		getPec().addEvent(new PheromoneEvaporationEvent(setNextEventTime(), startNode, endNode));
    	}
    }


    private static double calculateEventTime(double time){
        Random random = new Random();
        double next = random.nextDouble();
        double newEventTime = time + (-ParameterReader.getEta()*Math.log(1.0-next));//get PEC time
        System.out.println("Pheromone evaporation event at time set next " + newEventTime);
        return newEventTime;
    }

    @Override
    public double setNextEventTime(){
        Random random = new Random();
        double next = random.nextDouble();
        double newEventTime = getPECTime() + (-ParameterReader.getEta()*Math.log(1.0-next));//get PEC time
        System.out.println("Pheromone evaporation event at time set next " + newEventTime);
        return newEventTime;
    }
}
