package cycle;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;
import graph.GraphFacade;
import main.ParameterReader;
import java.util.BitSet;
import event.EventManager;
import event.PheromoneEvaporationEvent;

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
	
	public boolean isLastNode(int element) {
		if(cycle.getLast() != null) {
			return (cycle.getLast()==element);
		}
		else {return false;}
	}
	
	public int getCurrentCycleWeight() {
		return currentCycleWeight;
    }
    
    public void incrementCurrentCycleWeight(int increment) {
    	currentCycleWeight = currentCycleWeight + increment;
    }
	
	public int removeCycle(int stopNod, BitSet nVNodes) {
		GraphFacade g = GraphFacade.getInstance();
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
			nVNodes.set(nextElement, true);
			previousElement = Integer.valueOf(nextElement.intValue());
			iterator.remove();
		}
		currentCycleWeight -= reduceWeight;
		return reduceWeight;
	}
	
	public void layP(EventManager PEC) {
		GraphFacade gr = GraphFacade.getInstance();
		if(currentCycleWeight == 0) {
			return;
		}
		float p = ParameterReader.getGamma()*(gr.getTotalWeight()/currentCycleWeight);
		Iterator<Integer> iterator = cycle.iterator();
		Integer previousNode = null;
		Integer currentNode = null;
		
		while (iterator.hasNext() || isLastNode(previousNode)) {
			
			if(iterator.hasNext()) {
				currentNode = iterator.next();
			}
			
			else {
				currentNode = ParameterReader.getNest();
			}
			
			if(previousNode != null) {
				double currentPheromones = gr.getPheromones(previousNode, currentNode);
				gr.increaseEdgePheromones(previousNode, currentNode, p);
				
				// if the pheromone level was 0 before the increase in the
				// level of pheromones, schedule a pheromone evaporation event
				
				if(currentPheromones == 0) {
					PEC.addEvent(new PheromoneEvaporationEvent(PEC.getTime(), previousNode, currentNode));
				}
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
