package graph;

import java.util.Arrays;
import java.util.Random;

class GraphGenerator {
	
	protected int[][] graph; // Graph
	
    public GraphGenerator(int numVertices) {
    	graph = new int[numVertices][numVertices];
    }
    
    private void setWeight(int i, int j, int w) {
    	graph[i][j] = w;
    }
    
    public int getWeight(int i, int j) {
    	return graph[i][j];
    }
    
    private static void shufflePerm(int[] perm) {
    	Random random = new Random();
        for (int i = perm.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = perm[i];
            perm[i] = perm[j];
            perm[j] = temp;
        }
    }
    
    private static int getRandomWeight(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
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
    
    public void fillGraphFile(String w[], int linecnt) {
    	for(int i = 0; i < graph[linecnt-1].length; i++){
    		this.setWeight(linecnt - 1, i, Integer.parseInt(w[i]));
        }
    }
    
    public void printGraph() {
    	for(int[] row : graph) {
    		System.out.print("\t\t\t");
    		for(int cell : row) {
    			System.out.print(cell + " ");
    		}
    		System.out.println();
    	}
    }
    
}

