package ant;
import java.util.BitSet;
import java.util.Map;
import java.util.LinkedHashMap;
import cycle.Cycle;
import event.EventManager;
import graph.GraphFacade;
import main.ParameterReader;
import java.util.Random;
import java.util.ArrayList;

/**
 * Represents an ant in the ant colony optimization algorithm.
 */
class Ant {
    private Cycle currentCycle;
    private BitSet nonVisitedNodes;
    private double meanEdgeTime;
	/**
	 * Constructs a new Ant object and initializes the nonVisitedNodes BitSet.
	 */
    public Ant() {
		this.nonVisitedNodes = new BitSet(ParameterReader.getN());
        nonVisitedNodes.set(0, ParameterReader.getN());
        int nest = ParameterReader.getNest();
        this.currentCycle = new Cycle();
        nonVisitedNodes.set(nest, false);
        meanEdgeTime = 0;
    }

    // Getters and Setters
	/**
     * Gets the current cycle of the ant.
     *
     * @return The current cycle of the ant.
     */
    public Cycle getCurrentCycle() {
        return currentCycle;
    }

	/**
	 * Sets the current cycle of the ant.
	 *
	 * @param c The new current cycle of the ant.
	 */
    public void setCurrentCycle(Cycle c) {
        currentCycle = c;
    }
    
	/**
     * Lay pheromones on the current cycle.
     *
     * @param PEC The event manager for handling events.
     */
    public void layPheromones(EventManager PEC) {
    	currentCycle.layP(PEC);
    }
    
	/**
     * Resets the non-visited nodes to include all nodes except the nest.
     */
    public void resetNonVisitedNodes() {
    	nonVisitedNodes.set(0, ParameterReader.getN());
    	nonVisitedNodes.set(ParameterReader.getNest(), false);
    }
    
	/**
     * Gets the mean edge time.
     *
     * @return The mean edge time.
     */
    public double getMeanEdgeTime() {
    	return meanEdgeTime;
    }
    
	/**
     * Sets the mean edge time based on the weight.
     *
     * @param weight The weight of the edge.
     */
    public void setMeanEdgeTime(int weight) {
    	meanEdgeTime = weight * ParameterReader.getDelta();
    }
    
	/**
     * Checks if there are any unvisited nodes.
     *
     * @return True if there are unvisited nodes, false otherwise.
     */
    public boolean AreThereUnvisitedNodes() {
		return !nonVisitedNodes.isEmpty();
	}

	/**
     * Handles the visited nodes.
     *
     * @param visitedN The list of visited nodes.
     * @return True if the cycle is completed, false otherwise.
     */
	public boolean handleVisitedNodes(ArrayList<Integer> visitedN) {
		Random randomN = new Random();
		int r = randomN.nextInt(visitedN.size());
		int next = visitedN.get(r);
		int edgeW = GraphFacade.getInstance().getWeight(currentCycle.getLastNode(), next);
		boolean completedCycle = false;
		// If the ant has visited all nodes and the next node is the nest, the cycle is completed.
		if((!AreThereUnvisitedNodes()) && (next == ParameterReader.getNest())) {
			completedCycle = true;
			currentCycle.incrementCurrentCycleWeight(edgeW);
		}
		// If the ant has visited all nodes and the next node is not the nest, the cycle is not completed.
		else {
			currentCycle.removeCycle(next, nonVisitedNodes);
		}
		setMeanEdgeTime(edgeW);
		return completedCycle;
	}

	/**
     * Gets the neighboring nodes, calculates their probabilities, and returns the sum of probabilities.
     *
     * @param g               The graph facade.
     * @param currentNode     The current node.
     * @param visitedN        The list of visited nodes.
     * @param probabilityList The map to store the probabilities of each neighboring node.
     * @return The sum of probabilities.
     */
	public double getNodes(GraphFacade g, int currentNode, ArrayList<Integer> visitedN, Map<Integer,Double> probabilityList){
		double sumProbabilities = 0;
		for(int i = 0; i < ParameterReader.getN(); i++){
			// If the node is not visited, calculate the probabilities.
			if(nonVisitedNodes.get(i)){
				sumProbabilities = calculateProbabilities(g, currentNode, i, probabilityList, sumProbabilities);
			} 
			else { // If the node is visited, add it to the visited list.
				if(g.getWeight(currentNode, i) != 0 && probabilityList.isEmpty()){
					visitedN.add(i);
				}
			}
		}
		return sumProbabilities;
	}

	/**
     * Calculates the probabilities of neighboring nodes and stores them in the probabilityList.
     *
     * @param g               The graph facade.
     * @param currentN        The current node.
     * @param i               The neighboring node.
     * @param probabilityList The map to store the probabilities of each neighboring node.
     * @param sumProbabilities The current sum of probabilities.
     * @return The updated sum of probabilities.
     */
	public double calculateProbabilities(GraphFacade g, int currentN, int i, Map<Integer, Double> probabilityList, double sumProbabilities){
		int w = g.getWeight(currentN, i);
		// If the weight is different than 0, calculate the probability and add it to the sum of probabilities.
		if(w != 0) {
			double probability = (ParameterReader.getAlpha() + g.getPheromones(currentN, i)) / (ParameterReader.getBeta() + w);
			sumProbabilities += probability;
			probabilityList.put(i, probability);
		}
		return sumProbabilities;
	}

	/**
     * Performs inverse transform sampling to choose the next node based on the probabilities.
     *
     * @param g               The graph facade.
     * @param currentN        The current node.
     * @param probabilityList The map containing the probabilities of each neighboring node.
     * @param sumProbabilities The sum of probabilities.
     */
	public void InverseTranformSampling(GraphFacade g, int currentN, Map<Integer, Double> probabilityList, double sumProbabilities){
		Random random = new Random();
		double u = random.nextDouble();
		double cumulativeProbability = 0;
		int w = 0;
		for(Map.Entry<Integer, Double> entry : probabilityList.entrySet()) {
			int node = entry.getKey();
			cumulativeProbability += (entry.getValue()/sumProbabilities);
			// If the cumulative probability is greater than the random number, choose the node
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

	/**
     * Chooses the next node to visit.
     *
     * @return True if the cycle is completed, false otherwise.
     */
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
    		else { // There are nodes that haven't been visited that are adjacent to the node
    			InverseTranformSampling(g, currentN, probabilityList, sumProbabilities);
    		}
    	} 	
    	else { // There are no nodes to visit
    		completeCycle = handleVisitedNodes(visitedN);
    	}
    	
		return completeCycle;
    }
}

