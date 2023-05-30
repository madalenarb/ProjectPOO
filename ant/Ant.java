package ant;
import java.util.BitSet;
import java.util.Map;
import java.util.LinkedHashMap;
import cycle.Cycle;
import graph.GraphFacade;
import main.ParameterReader;
import java.util.Random;
import java.util.ArrayList;


class Ant {
    private Cycle currentCycle;
    private BitSet visitedNodes;

    public Ant(int id) {
        visitedNodes.set(0, ParameterReader.getN(), false);
        int nest = ParameterReader.getNest();
        this.currentCycle = new Cycle();
        visitedNodes.set(nest, true);
    }

    // Getters and Setters

    public Cycle getCurrentCycle() {
        return currentCycle;
    }
    
    public void setCurrentCycle(Cycle c) {
        currentCycle = c;
    }
    
    public void layPheromones() {
    	currentCycle.layP();
    }
    
    public int chooseNextNode() {
    	GraphFacade g = ParameterReader.getGraphFacade();
    	int nonVisitedNodes = 0;
    	Map<Integer, Double> probabilityList = new LinkedHashMap<>();
    	double sumProbabilities = 0;
    	ArrayList<Integer> visitedN = new ArrayList<>();
    	int lengthVisitedN = 0;
    	int n = ParameterReader.getN();
    	int currentN = currentCycle.getLastNode();
    	
    	for(int i = 0; i < n; i++) {
    		if(!visitedNodes.get(currentN)) {
    			nonVisitedNodes = 1;
    			int w = g.getWeight(currentN, i);
    			if(w != 0) {
    				double probability = (ParameterReader.getAlpha() + g.getPheromones(currentN, i)) / (ParameterReader.getBeta() + w);
    				sumProbabilities += probability;
    				probabilityList.put(i, probability);
    			}
    		}
    		else {
    			if(g.getWeight(currentN, i) != 0 && probabilityList.isEmpty()) {
    				visitedN.add(i);
    				++lengthVisitedN;
    			}
    		}
    	}
    	
    	// There are still nodes to visit
    	if(nonVisitedNodes == 1) {
    		if(probabilityList.isEmpty()) {
    			// The nodes that haven't been visited aren't adjacent to the node
    			Random randomN = new Random();
    			int r = randomN.nextInt(lengthVisitedN);
    			int cycleNode = visitedN.get(r);
    			// Go back and find the beginning of the cycle
    			// Eliminate the cycle and update the current cycle weight
    			currentCycle.removeCycle(cycleNode);
    		}
    		else {
    			Random random = new Random();
    	        double u = random.nextDouble();
    	        double cumulativeProbability = 0;
    	        for(Map.Entry<Integer, Double> entry : probabilityList.entrySet()) {
    	        	int node = entry.getKey();
    	        	cumulativeProbability += (entry.getValue()/sumProbabilities);
    	        	if(cumulativeProbability >= u) {
    	        		// Update the weight
    	        		currentCycle.incrementCurrentCycleWeight(g.getWeight(currentN, node));
    	        		// Add the node to end of the Cycle
    	        		currentCycle.addNode(node);
    	        		visitedNodes.set(node, true);
    	        		break;
    	        	}
    	        }
    		}
    	}
    	else {
    		currentCycle.incrementCurrentCycleWeight(g.getWeight(currentCycle.getLastNode(), ParameterReader.getNest()));
    	}
    	
    	return nonVisitedNodes; //if there are no nodes left to visit the function returns 0
    }
}

