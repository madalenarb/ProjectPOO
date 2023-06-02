package ant;
import java.util.BitSet;
import java.util.Map;
import java.util.LinkedHashMap;
import cycle.Cycle;
import graph.GraphFacade;
import main.ParameterReader;
import java.util.Random;
//import java.lang.reflect.Array;
import java.util.ArrayList;


class Ant {
    private Cycle currentCycle;
    private BitSet visitedNodes;

    public Ant(int id) {
		this.visitedNodes = new BitSet(ParameterReader.getN());
        visitedNodes.set(0, ParameterReader.getN(), false);
        int nest = ParameterReader.getNest();
        this.currentCycle = new Cycle();
        visitedNodes.set(nest, true);
    }

    // Getters and Setters

    public Cycle getCurrentCycle() {
        return currentCycle;
    }
    
	public boolean AreThereUnvisitedNodes() {
		return !visitedNodes.isEmpty();
	}

    public void setCurrentCycle(Cycle c) {
        currentCycle = c;
    }
    
    public void layPheromones() {
    	currentCycle.layP();
    }
    
	public ArrayList<Integer> getAdjNodes(int currentNode) {
		ArrayList<Integer> adjNodes = new ArrayList<>();
		GraphFacade g = ParameterReader.getGraphFacade();
		int n = ParameterReader.getN();
		for(int i = 0; i < n; i++) {
			if(g.getWeight(currentNode, i) != 0) {
				adjNodes.add(i);
			}
		}
		return adjNodes;
	}

	public void removeVisitedNodesfromCycle(int currentNode, ArrayList<Integer> adjNodes) {
		Random randomN = new Random();
		int r = randomN.nextInt(adjNodes.size());
		currentCycle.removeCycle(adjNodes.get(r));
		currentCycle.addNode(adjNodes.get(r));
	}
	public double getNodes(GraphFacade g, int currentNode, ArrayList<Integer> visitedN, Map<Integer,Double> probabilityList){
		double sumProbabilities = 0;
		for(int i = 0; i < ParameterReader.getN(); i++){
			if(!visitedNodes.get(currentNode)){
				sumProbabilities = calculateProbabilities(g, currentNode, i, probabilityList, sumProbabilities);
			} else {
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

	public void handleNonVisitedNodes(GraphFacade g, int currentN, ArrayList<Integer> visitedN, Map<Integer, Double> probabilityList, ArrayList<Integer> adjNodes, Double sumProbabilities){
		if(probabilityList.isEmpty()) {
			// The nodes that haven't been visited aren't adjacent to the node
			removeVisitedNodesfromCycle(currentN, adjNodes);
		} else {
			selectNodeBasedonProbability(g, currentN, probabilityList, sumProbabilities);
		}
	}

	public void handleAllVisitedNodes(GraphFacade g, ArrayList<Integer> visitedN, ArrayList<Integer> adjNodes, int currentNode){
		if(g.getWeight(currentCycle.getLastNode(), ParameterReader.getNest()) != 0){
			currentCycle.incrementCurrentCycleWeight(g.getWeight(currentCycle.getLastNode(), ParameterReader.getNest()));
		} else {
			// If there isn't, it randomly chooses any adjacent node with a uniform distribution, it should update its 
			// path by removing the cycle created in the last move
			removeVisitedNodesfromCycle(currentNode, adjNodes);
		}
	}

	public void selectNodeBasedonProbability(GraphFacade g, int currentN, Map<Integer, Double> probabilityList, Double sumProbabilities){
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

    public boolean chooseNextNode() {
    	GraphFacade g = ParameterReader.getGraphFacade();
    	Map<Integer, Double> probabilityList = new LinkedHashMap<>();
    	double sumProbabilities = 0;
    	ArrayList<Integer> visitedN = new ArrayList<>();
    	int currentN = currentCycle.getLastNode();
		ArrayList<Integer> adjNodes = getAdjNodes(currentN);
    	sumProbabilities = getNodes(g, currentN, visitedN, probabilityList);
    	
    	// There are still nodes to visit
    	if(AreThereUnvisitedNodes()) {
    		handleNonVisitedNodes(g, currentN, visitedN, probabilityList, adjNodes, sumProbabilities);
    	} else {
			handleAllVisitedNodes(g, visitedN, adjNodes, currentN);
    	}
		return AreThereUnvisitedNodes();
    }
}

