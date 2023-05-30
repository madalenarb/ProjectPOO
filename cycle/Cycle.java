package cycle;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;
import graph.GraphFacade;
import main.ParameterReader;

public class Cycle {
	private LinkedList<Integer> cycle;
	int currentCycleWeight;
	
	public Cycle() {
        cycle = new LinkedList<>();
        cycle.add(ParameterReader.getNest());
        currentCycleWeight = 0;
    }
	
	public void addNode(int nod) {
        cycle.add(nod);
    }
	
	public int getLastNode() {
		return cycle.getLast();
	}
	
	public int getCurrentCycleWeight() {
    	return currentCycleWeight;
    }
    
    public void incrementCurrentCycleWeight(int increment) {
    	currentCycleWeight = currentCycleWeight + increment;
    }
	
	public void removeCycle(int stopNod) {
		GraphFacade g = ParameterReader.getGraphFacade();
		ListIterator<Integer> iterator = cycle.listIterator(cycle.size());
		int reduceWeight = 0;
		Integer previousElement = null;
		while (iterator.hasPrevious()) {
			Integer nextElement = (Integer) iterator.previous();
			if(previousElement != null) {
				reduceWeight += g.getWeight(previousElement, nextElement);
			}
			if(nextElement.equals(stopNod)) {
				break;
			}
			previousElement = nextElement;
			iterator.remove();
		}
		currentCycleWeight -= reduceWeight;
	}
	
	public void layP() {
		GraphFacade gr = ParameterReader.getGraphFacade();
		float p = (ParameterReader.getGamma()*(currentCycleWeight - gr.getWeight(cycle.getLast(), ParameterReader.getNest())))/currentCycleWeight;
		Iterator<Integer> iterator = cycle.iterator();
		Integer previousNode = null;
		while (iterator.hasNext()) {
			Integer currentNode = iterator.next();
			if(previousNode != null) {
				gr.increaseEdgePheromones(previousNode,currentNode, p);
			}
			previousNode = currentNode;
		}
	}
	
	public void printElements() {
    	System.out.print("{");
    	Iterator<Integer> iterator = cycle.iterator();
        while (iterator.hasNext()) {
        	Integer current = iterator.next();
            System.out.print(current);
            if(iterator.hasNext()) {
            	System.out.print(",");
            }
        }
        System.out.print("}:" + currentCycleWeight);
        //System.out.println();
    }
}
