/**
 * Contains classes related to cycles in the ant colony optimization algorithm.
 * Cycles are represented as linked lists of nodes and play a crucial role in the
 * optimization process.
 * 
 * <p>The package includes the following classes:</p>
 * <ul>
 *   <li>{@link cycle.Cycle}: Represents a cycle in the graph with its associated weight.
 *     Provides methods for managing and manipulating the cycle.</li>
 *   <ul>
 *     <li>{@link cycle.Cycle#getCycleList()}: Returns an unmodifiable view of the cycle.</li>
 *     <li>{@link cycle.Cycle#addNode(int)}: Adds a new node to the cycle.</li>
 *     <li>{@link cycle.Cycle#getLastNode()}: Returns the last node in the cycle.
 *       If the cycle is empty, returns -1.</li>
 *     <li>{@link cycle.Cycle#isLastNode(Integer)}: Checks if the provided node is the last one in the cycle.</li>
 *     <li>{@link cycle.Cycle#getCurrentCycleWeight()}: Retrieves the current weight of the cycle.</li>
 *     <li>{@link cycle.Cycle#incrementCurrentCycleWeight(int)}: Increments the current cycle weight by a given amount.</li>
 *     <li>{@link cycle.Cycle#removeCycle(int, java.util.BitSet)}: Removes nodes from the cycle up to a given stop node
 *       and updates the current cycle weight.</li>
 *     <li>{@link cycle.Cycle#layP(event.EventManager)}: Lays pheromones in the cycle based on the current cycle weight
 *       and schedules pheromone evaporation events.</li>
 *     <li>{@link cycle.Cycle#printElements()}: Prints all elements of the cycle as a comma-separated string surrounded by
 *       braces, along with the current cycle weight.</li>
 *   </ul>
 * </ul>
 * 
 * <p>These classes provide the necessary functionality to create, modify, and analyze cycles within the ant colony optimization
 * algorithm. They are used to track the paths traversed by the ants and facilitate pheromone updates and evaporation.</p>
 * 
 */
package cycle;
