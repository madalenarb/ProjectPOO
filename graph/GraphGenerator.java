package graph;

import java.util.Arrays;
import java.util.Random;

import main.utils.ConsoleFilePrinters;

/**
 * GraphGenerator class.
 * Utility class for generating a graph representation, with the ability to set weights, 
 * get weights, generate random permutations and weights, and fill the graph 
 * in no file mode or with a file.
 * 
 * <p>Class Parameters:</p>
 * <ul>
 * <li>{@code graph} - the graph.</li>
 * </ul>
 */
class GraphGenerator {
	
	protected int[][] graph; // Graph
	
    /**
     * Creates a GraphGenerator with a specified number of vertices.
     * 
     * @param numVertices The number of vertices in the graph.
     */
    public GraphGenerator(int numVertices) {
    	graph = new int[numVertices][numVertices];
    }
    
    
    /**
     * Sets the weight of an edge between two vertices in the graph.
     * 
     * @param i The first vertex.
     * @param j The second vertex.
     * @param w The weight of the edge between vertices i and j.
     */
    private void setWeight(int i, int j, int w) {
    	graph[i][j] = w;
    }
    
    /**
     * Gets the weight of an edge between two vertices in the graph.
     * 
     * @param i The first vertex.
     * @param j The second vertex.
     * @return The weight of the edge between vertices i and j.
     */
    public int getWeight(int i, int j) {
    	return graph[i][j];
    }

    /**
     * Shuffles a given permutation array.
     * 
     * @param perm The permutation array to shuffle.
     */
    private static void shufflePerm(int[] perm) {
    	Random random = new Random();
        for (int i = perm.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = perm[i];
            perm[i] = perm[j];
            perm[j] = temp;
        }
    }
    
    /**
     * Generates a random weight between a specified range.
     * 
     * @param min The minimum weight value.
     * @param max The maximum weight value.
     * @return A random weight between min and max (inclusive).
     */
    private static int getRandomWeight(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    /**
     * Fills the graph in no file mode with randomly generated weights.
     * 
     * @param n The number of vertices in the graph.
     * @param maxW The maximum weight value.
     */
    public void fillGraphNoFile(int n, int maxW) {
    	
    	int[] permutation = new int[n];
    	int num = 0;
    	int weight = 0;
    	
    	for (int[] row : graph) {
            Arrays.fill(row, 0);
            permutation[num] = num;
            ++num;
        }
    	
    	// Generate random permutation of numbers 0 to n-1
        shufflePerm(permutation);
    	
        // Create edges between consecutive nodes in the permutation
        int prevNode = permutation[n - 1];
        for (int currNode : permutation) {
            weight = getRandomWeight(1, maxW);
            this.setWeight(prevNode, currNode, weight);
            this.setWeight(currNode, prevNode, weight);
            prevNode = currNode;
        }
        
        // Given that the previous code created n edges
        // (the minimal to create a hamiltonian cycle),
        // we will sample a random number between 0 and
        // (n*(n-1))/2 - n
        
        // Calculate the upper bound
        int upperBound = ((n * (n - 1)) / 2) - n;
        
        Random random = new Random();
        
        // add one to the upper bound to include it
        int randomInt = random.nextInt(upperBound + 1);
        
        // Create random edges between nodes
        for(int i = 0; i < randomInt; i++) {
        	while(true) {
        		
        		int randomNode1 = random.nextInt(n);
        		int randomNode2 = random.nextInt(n);
        	
        		if(getWeight(randomNode1, randomNode2) == 0 && randomNode1 != randomNode2) {
        			weight = getRandomWeight(1, maxW);
        			setWeight(randomNode1, randomNode2, weight);
        			setWeight(randomNode2, randomNode1, weight);
        			break;
        		}
        	
        	}
        	
        }
        
    }
    
    /**
     * Fills the graph with weights from a file.
     * 
     * @param w An array of weights.
     * @param linecnt The line number in the file.
     */
    public void fillGraphFile(String w[], int linecnt) {
    	for(int i = 0; i < graph[linecnt-1].length; i++){
    		this.setWeight(linecnt - 1, i, Integer.parseInt(w[i]));
        }
    }
    
    /**
     * Prints the graph.
     */
    public void printGraph() {
        StringBuilder sb = new StringBuilder();
    	for(int[] row : graph) {
    		sb.append("\t\t\t");
    		for(int cell : row) {
    			sb.append(cell + " ");
    		}
    		sb.append("\n");
    	}
        ConsoleFilePrinters.getInstance().printf(sb.toString());
    }
    
}

