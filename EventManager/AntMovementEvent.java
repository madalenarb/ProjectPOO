package EventManager;

import ant.AntColony;

public class AntMovementEvent implements Event{
    private double time;
    private AntColony antColony;
    private int antID;

    public AntMovementEvent(double time, AntColony antColony, int antID) {
        this.time = time;
        this.antColony = antColony;
        this.antID = antID;
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