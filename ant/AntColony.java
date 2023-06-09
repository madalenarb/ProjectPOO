package ant;
import java.util.Comparator;
import java.util.PriorityQueue;
import main.ParameterReader;
import cycle.Cycle;
import event.EventManager;


public class AntColony {
	private static AntColony instance;
	private Ant[] ants;
	private PriorityQueue<Cycle> hamiltonianCycleQueue;
	private Cycle best;
	
	private AntColony() {
		int n = ParameterReader.getNu();
		ants = new Ant[n];
		for(int i = 0; i < n; i++) {
			ants[i] = new Ant(i);
		}
		hamiltonianCycleQueue = new PriorityQueue<>(Comparator.comparingInt(Cycle::getCurrentCycleWeight).reversed());
		best = null;
	}
	
	public static AntColony getInstance() {
        if (instance == null) {
        	synchronized (AntColony.class) {
                if (instance == null) {
                    instance = new AntColony();
                }
        	}
        }
        return instance;
    }
	
	public void printBestHamiltonianCycle() {
		System.out.printf("\t\t\t");
		best.printElements();
	}
	
	public void printTopCycles() {
		for(Cycle c : hamiltonianCycleQueue) {
			System.out.printf("\t\t\t");
			c.printElements();
			System.out.println();
		}
	}
	
	public void addCycleToPQ(Cycle c){
		hamiltonianCycleQueue.offer(c);
		if(hamiltonianCycleQueue.size() > 5) {
			hamiltonianCycleQueue.poll();
		}
	}
	
	public boolean moveAnt(int i) {
		boolean completeCycle = !ants[i].chooseNextNode();
		if(completeCycle) { // the ant completed a cycle
			// Compare the cycle with the best cycle
			if(best == null) {
				best = ants[i].getCurrentCycle();
			}
			else {
				if(best.getCurrentCycleWeight() > ants[i].getCurrentCycle().getCurrentCycleWeight()) {
					best = ants[i].getCurrentCycle();
				}
				else {
					// Compare the cycle with the top 5 cycles
					addCycleToPQ(ants[i].getCurrentCycle());
				}
			}
			return true;
		}
		return false;
	}
	
	public void restartPath(int i) {
		ants[i].setCurrentCycle(new Cycle());
	}
	
	public void antLaysPheromones(int i, EventManager PEC) {
		ants[i].layPheromones(PEC);
	}
	
	public double meanTraverseTime(int i) {
		return ants[i].getMeanEdgeTime();
	}
	
}
