package ant;
import java.util.Comparator;
import java.util.PriorityQueue;
import main.ParameterReader;
import cycle.Cycle;


public class AntColony {
	private Ant[] ants;
	private PriorityQueue<Cycle> hamiltonianCycleQueue;
	private Cycle best;
	
	public AntColony() {
		int n = ParameterReader.getN();
		ants = new Ant[n];
		for(int i = 0; i < n; i++) {
			ants[i] = new Ant(i);
		}
		hamiltonianCycleQueue = new PriorityQueue<>(Comparator.comparingInt(Cycle::getCurrentCycleWeight).reversed());
		best = null;
	}
	
	public void addCycleToPQ(Cycle c){
		hamiltonianCycleQueue.offer(c);
		if(hamiltonianCycleQueue.size() > 5) {
			hamiltonianCycleQueue.poll();
		}
	}
	
	public void moveAnt(int i) {
		int NVnodes = ants[i].chooseNextNode();
		if(NVnodes == 0) { // the ant completed a cycle
			ants[i].layPheromones();
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
					this.addCycleToPQ(ants[i].getCurrentCycle());
				}
			}
			// Restart the graph
			ants[i].setCurrentCycle(new Cycle());
		}
	}
}
