package EventManager;

import ant.AntColony;

public class AntMovementEvent implements Event{
    private double time;
    private AntColony antColony;
    private int antID;
    private int startNode;
    private int endNode;

    public AntMovementEvent(double time, AntColony antColony, int antID, int startNode, int endNode) {
        this.time = time;
        this.antColony = antColony;
        this.antID = antID;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    @Override
    public void executeEvent() {
        antColony.moveAnt(antID);
    }

    @Override
    public double getEventTime() {
        return time;
    }
}