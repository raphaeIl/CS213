package client;

import command.CommandManager;

import java.util.Scanner;

/**
 * Main User Interface class that process command lines entered to the IDE console and display
 * the results on the console.
 * @author Michael Liu, Genfu Liu
 */
public class GymManager {

    /**
     * Starts the user interface, continuously reads user input command lines,
     * and passes them to CommandManager to handle the inputs
     */
    public void run() {
        System.out.println("Gym Manager running...");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int result = CommandManager.getInstance().invoke(scanner.nextLine()); // invokes the input command

            if (result == CommandManager.EXECUTE_EXIT)
                break;
        }

        System.out.println("Gym Manager terminated.");
    }

    /**
     * Since this is the main client interface class, it will also be responsible for logging messages,
     * such as helpful info as well as errors/warnings,
     * these can also be toggled with a boolean variable like boolean debug = true;
      * @param message the message to be logged
     */
    public static void log(String message) {
        System.out.println(message);
    }

    /**
     * Logs a message on the console with formatting
     * @param format a format string of the message to be logged
     * @param args arguments for the format string
     */
    public static void logf(String format, Object ... args) {
        System.out.printf(format, args);
    }
}
