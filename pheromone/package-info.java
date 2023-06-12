/**
 * The pheromone package contains classes that manage the pheromone levels on the edges of the graph in the Ant Colony Optimization algorithm.
 * It includes a class for representing the pheromone map and methods for adding, removing, and accessing the pheromone levels.
 *
 * <p>The main classes in this package are:
 * - {@link pheromone.PheromoneMap}: Represents a map of pheromones laid on the edges of the graph. It provides methods for managing and accessing the pheromone levels.
 *   - {@code addPheromone(int startNode, int endNode, double pheromoneLevel)}: Adds pheromones to the edge between two nodes in the graph.
 *   - {@code removePheromone(int startNode, int endNode)}: Removes pheromones from the edge between two nodes in the graph.
 *   - {@code getPheromoneLevel(int startNode, int endNode)}: Gets the level of pheromones on the edge between two nodes in the graph.
 *   - {@code increasePheromoneLevel(int startNode, int endNode, double increasePheromones)}: Increases the level of pheromones on the edge between two nodes in the graph.
 *   - {@code reducePheromoneLevel(double decrement, int startNode, int endNode)}: Reduces the level of pheromones on the edge between two nodes in the graph.
 *   - {@code printEdges()}: Prints all edges in the graph and their associated pheromone levels.
 *
 * <p>The pheromone levels are stored in a map where the keys are arrays of integers representing the start and end nodes of an edge,
 * and the values are the corresponding pheromone levels.
 */
package pheromone;
