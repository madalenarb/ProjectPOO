/**
 * The main package contains classes that control the execution flow of the Ant Colony Optimization algorithm.
 * It includes the entry point for running the algorithm, parameter reading and management, and message printing.
 *
 * <p>The main classes in this package are:</p>
 * <ul>
 *   <li>{@link main.Main}: The main class for running the Ant Colony Optimization algorithm. It initializes the necessary components, such as the ant colony and event manager, and runs the simulation.
 *     <ul>
 *       <li>{@code main(String[] args)}: The entry point for running the Ant Colony Optimization algorithm. It reads the command line arguments, initializes the necessary components, and starts the simulation.</li>
 *     </ul>
 *   </li>
 *   <li>{@link main.ParameterReader}: A class for reading and storing the parameters from the command line arguments or an input file. It provides methods to retrieve the parameter values for different components of the algorithm.
 *   </li>
 *   <li>{@link main.utils.MessageError}: A utility class for printing error messages and correct usage instructions. It includes methods to display error messages and provide guidance on correct command usage.
 *   </li>
 *   <li>{@link main.utils.CmdTextFormatter}: A class that defines ANSI escape sequences for text formatting in the command line. It provides constants for text styling, such as bold, underline, and colors.</li>
 *   <li>{@link main.utils.ConsolePrinter}: A class that implements the {@link main.utils.Printer} interface and provides functionality for printing text to the console.</li>
 *   <li>{@link main.utils.PrinterController}: A class that provides functionality for printing text to both the console and a file. It supports different printer types, such as console-only, file-only, or both.</li>
 *   <li>{@link main.utils.FilePrinter}: A class that implements the {@link main.utils.Printer} interface and provides functionality for printing text to a file.</li>
 *   <li>{@link main.utils.Printer}: An interface that defines methods for printing text.</li>
 *   <li>{@link main.utils.PrinterType}: An enum that represents the type of printer to use (CONSOLE, FILE, or BOTH).</li>
 * </ul>
 *
 * <p>By utilizing the classes in this package, the Ant Colony Optimization algorithm can be executed and controlled
 * with the provided parameters, and relevant messages can be printed for user interaction and feedback.</p>
 *
 * <p><b>Authors:</b></p>
 * <ul>
 *   <li>Ana Ferreira</li>
 *   <li>Diogo Neves</li>
 *   <li>Madalena Barros</li>
 * </ul>
 *
 * @see main.Main
 * @see main.ParameterReader
 * @see main.utils.MessageError
 * @see main.utils.CmdTextFormatter
 * @see main.utils.ConsolePrinter
 * @see main.utils.PrinterController
 * @see main.utils.FilePrinter
 * @see main.utils.Printer
 * @see main.utils.PrinterType
 */
package main;
