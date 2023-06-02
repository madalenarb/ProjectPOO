package EventManager;

import graph.GraphFacade;

public class PheromoneEvaporation implements Event{
    private double time;
    private GraphFacade graph;
    private int StartNode;
    private int EndNode;
    private double evaporationRate;



    public PheromoneEvaporation(double time, GraphFacade graph, int StartNode, int EndNode, double evaporationRate) {
        this.time = time;
        this.graph = graph;
        this.StartNode = StartNode;
        this.EndNode = EndNode;
        this.evaporationRate = evaporationRate;
    }

    @Override
    public void executeEvent() {
        graph.reducePheromones(StartNode, EndNode);
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
