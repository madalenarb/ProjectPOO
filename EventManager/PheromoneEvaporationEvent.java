package EventManager;

import graph.GraphFacade;

public class PheromoneEvaporationEvent implements Event{
    private double time;
    private GraphFacade graph;
    private int startNode;
    private int endNode;
    private double evaporationRate;
    



    public PheromoneEvaporationEvent(double time, GraphFacade graph, int startNode, int endNode, double evaporationRate) {
        this.time = time;
        this.graph = graph;
        this.startNode = startNode;
        this.endNode = endNode;
        this.evaporationRate = evaporationRate;
    }

    @Override
    public void executeEvent() {
        graph.reducePheromones(startNode, endNode);
    }

    @Override
    public double getEventTime() {
        return time;
    }

    public void setEvaporationRate(double evaporationRate) {
        this.evaporationRate = evaporationRate;
    }

    public double getEvaporationRate() {
        return evaporationRate;
    }
}