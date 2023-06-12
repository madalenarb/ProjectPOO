package ant;
import java.util.BitSet;
import java.util.Map;
import java.util.LinkedHashMap;
import cycle.Cycle;
import event.EventManager;
import graph.GraphFacade;
import main.ParameterReader;
import java.util.Random;
//import java.lang.reflect.Array;
import java.util.ArrayList;


class Ant {
    private Cycle currentCycle;
    private BitSet nonVisitedNodes;
    private double meanEdgeTime;

    public Ant() {
		this.nonVisitedNodes = new BitSet(ParameterReader.getN());
        nonVisitedNodes.set(0, ParameterReader.getN());
        int nest = ParameterReader.getNest();
        this.currentCycle = new Cycle();
        nonVisitedNodes.set(nest, false);
        meanEdgeTime = 0;
    }

    // Getters and Setters

    public Cycle getCurrentCycle() {
        return currentCycle;
    }

    public void setCurrentCycle(Cycle c) {
        currentCycle = c;
    }
    
    public void layPheromones(EventManager PEC) {
    	currentCycle.layP(PEC);
    }
    
    public void resetNonVisitedNodes() {
    	nonVisitedNodes.set(0, ParameterReader.getN());
    	nonVisitedNodes.set(ParameterReader.getNest(), false);
    }
    
    public double getMeanEdgeTime() {
    	return meanEdgeTime;
    }
    
    public void setMeanEdgeTime(int weight) {
    	meanEdgeTime = weight * ParameterReader.getDelta();
    }
    
    public boolean AreThereUnvisitedNodes() {
		return !nonVisitedNodes.isEmpty();
	}

	public boolean handleVisitedNodes(ArrayList<Integer> visitedN) {
		Random randomN = new Random();
		int r = randomN.nextInt(visitedN.size());
		int next = visitedN.get(r);
		int edgeW = GraphFacade.getInstance().getWeight(currentCycle.getLastNode(), next);
		boolean completedCycle = false;
		if((!AreThereUnvisitedNodes()) && (next == ParameterReader.getNest())) {
			completedCycle = true;
			currentCycle.incrementCurrentCycleWeight(edgeW);
		}
		else {
			currentCycle.removeCycle(next, nonVisitedNodes);
		}
		setMeanEdgeTime(edgeW);
		return completedCycle;
	}

	public double getNodes(GraphFacade g, int currentNode, ArrayList<Integer> visitedN, Map<Integer,Double> probabilityList){
		double sumProbabilities = 0;
		for(int i = 0; i < ParameterReader.getN(); i++){
			if(nonVisitedNodes.get(i)){
				sumProbabilities = calculateProbabilities(g, currentNode, i, probabilityList, sumProbabilities);
			} 
			else {
				if(g.getWeight(currentNode, i) != 0 && probabilityList.isEmpty()){
					visitedN.add(i);
				}
			}
		}
		return sumProbabilities;
	}

	public double calculateProbabilities(GraphFacade g, int currentN, int i, Map<Integer, Double> probabilityList, double sumProbabilities){
		int w = g.getWeight(currentN, i);
		if(w != 0) {
			double probability = (ParameterReader.getAlpha() + g.getPheromones(currentN, i)) / (ParameterReader.getBeta() + w);
			sumProbabilities += probability;
			probabilityList.put(i, probability);
		}
		return sumProbabilities;
	}

	public void InverseTranformSampling(GraphFacade g, int currentN, Map<Integer, Double> probabilityList, double sumProbabilities){
		Random random = new Random();
		double u = random.nextDouble();
		double cumulativeProbability = 0;
		int w = 0;
		for(Map.Entry<Integer, Double> entry : probabilityList.entrySet()) {
			int node = entry.getKey();
			cumulativeProbability += (entry.getValue()/sumProbabilities);
			if(cumulativeProbability >= u) {
				w = g.getWeight(currentN, node);
				// Update the weight
				currentCycle.incrementCurrentCycleWeight(w);
				// Add the node to end of the Cycle
				currentCycle.addNode(node);
				nonVisitedNodes.set(node, false);
				// Set the mean of the time to traverse the edge
				setMeanEdgeTime(w);
				break;
			}
		}
	}

    public boolean chooseNextNode() {
    	GraphFacade g = GraphFacade.getInstance();
    	boolean completeCycle = false;
    	Map<Integer, Double> probabilityList = new LinkedHashMap<>();
    	ArrayList<Integer> visitedN = new ArrayList<>();
    	int currentN = currentCycle.getLastNode();
    	double sumProbabilities = getNodes(g, currentN, visitedN, probabilityList);
    	
    	// There are still nodes to visit
    	if(AreThereUnvisitedNodes()) {
    		if(probabilityList.isEmpty()) {
    			// The nodes that haven't been visited aren't adjacent to the node
    			completeCycle = handleVisitedNodes(visitedN);
    		}   		
    		else {
    			InverseTranformSampling(g, currentN, probabilityList, sumProbabilities);
    		}
    	} 	
    	else {
    		completeCycle = handleVisitedNodes(visitedN);
    	}
    	
		return completeCycle;
    }
}

