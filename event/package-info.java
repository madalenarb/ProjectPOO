/**
 * This package contains classes related to events in the simulation.
 * Events play a crucial role in the simulation's execution flow and
 * facilitate the coordination and interaction between different components.
 * 
 * The main classes in this package are:
 * 
 * <ul>
 * <li>{@link event.Event}: An abstract class representing an event in the simulation.
 *     <ul>
 *     <li>{@link event.Event#executeEvent()}: Executes the event.</li>
 *     <li>{@link event.Event#getEventTime()}: Gets the time of the event.</li>
 *     <li>{@link event.Event#getPec()}: Gets the event manager associated with the event.</li>
 *     <li>{@link event.EventInterface}: An interface that provides a standard set of methods for event classes to implement.</li>
 *     </ul>
 * </li>
 * <li>{@link event.EventManager}: A class responsible for managing events and their execution.
 *     <ul>
 *     <li>{@link event.EventManager#getInstance()}: Gets the singleton instance of the event manager.</li>
 *     <li>{@link event.EventManager#addEvent(event.Event)}: Adds an event to the event manager.</li>
 *     <li>{@link event.EventManager#getEvent()}: Retrieves the next event from the event manager.</li>
 *     <li>{@link event.EventManager#initializePEC()}: Initializes the event manager with initial events.</li>
 *     <li>{@link event.EventManager#run()}: Runs the simulation by executing events until completion.</li>
 *     </ul>
 * </li>
 * <li>{@link event.AntMoveEvent}: A subclass of {@link event.Event} that represents the movement of an ant.
 *     <ul>
 *     <li>{@link event.AntMoveEvent#executeEvent()}: Executes the ant movement event.</li>
 *     <li>{@link event.AntMoveEvent#setNextEventTime()}: Sets the time for the next ant movement event.</li>
 *     </ul>
 * </li>
 * <li>{@link event.PheromoneEvaporationEvent}: A subclass of {@link event.Event} that represents pheromone evaporation.
 *     <ul>
 *     <li>{@link event.PheromoneEvaporationEvent#executeEvent()}: Executes the pheromone evaporation event.</li>
 *     <li>{@link event.PheromoneEvaporationEvent#setNextEventTime()}: Sets the time for the next pheromone evaporation event.</li>
 *     </ul>
 * </li>
 * <li>{@link event.PrintEvent}: A subclass of {@link event.Event} that represents a print event for observation.
 *     <ul>
 *     <li>{@link event.PrintEvent#executeEvent()}: Executes the print event.</li>
 *     <li>{@link event.PrintEvent#setNextEventTime()}: Sets the time for the next print event.</li>
 *     </ul>
 * </li>
 * </ul>
 * 
 * These classes work together to drive the simulation's behavior and handle various events and their execution.
 * By utilizing the event system, the simulation can progress in a controlled and coordinated manner.
 * 
 * Additionally, this package defines the {@link event.EventInterface} interface, which provides a standard set
 * of methods for event classes to implement. This allows for easy comparison and sorting of events.
 * 
 */
package event;
