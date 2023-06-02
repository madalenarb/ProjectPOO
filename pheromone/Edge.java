package pheromone;

class Edge {
    private int startNode;
    private int endNode;

    // Constructor
    Edge(int startNode, int endNode) {
        this.startNode = Math.min(startNode, endNode);
        this.endNode = Math.max(startNode, endNode);
    }

    // Getters and setters
    int getStartNode() {
        return startNode;
    }

    void setStartNode(int startNode) {
        this.startNode = startNode;
    }

    int getEndNode() {
        return endNode;
    }

    void setEndNode(int endNode) {
        this.endNode = endNode;
    }

    // float getPheromoneLevel() {
    //     return pheromoneLevel;
    // }

    // void setPheromoneLevel(float pheromoneLevel) {
    //     this.pheromoneLevel = pheromoneLevel;
    // }
}

