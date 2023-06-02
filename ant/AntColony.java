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
		int nu = ParameterReader.getNu();
		System.out.println("N: " + nu);
		ants = new Ant[nu];
		for(int i = 0; i < nu; i++) {
			System.out.println("Ant "+i);
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
	
	// see if the ants have completed a cycle
	public boolean hasCompletedCycle(){
		for(Ant ant : ants){
			if(ant.getCurrentCycle().isComplete())
				return true;
		}
		return false;
	}

	public void moveAnt(int i) {
		boolean NVnodes = ants[i].chooseNextNode();
		if(!NVnodes) { // the ant completed a cycle
			ants[i].layPheromones();//não podemos- ter em atenção que antes disto temos de ver se o nivel de feromonas é positivo para ver se add ou nao o evento
			//ver se no map feromonas existe um elemento que tenha os nodes da aresta que vamos meter feromonas
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