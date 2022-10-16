package client;

import command.CommandManager;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Main User Interface class that process command lines entered to the IDE console and display
 * the results on the console.
 * @author Michael Liu, Genfu Liu
 */
public class GymManager {

    /**
     * Starts the user interface, continuously reads user input command lines
     */
    public void run() {
        System.out.println("Gym Manager running...");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int result = CommandManager.getInstance().invoke(scanner.nextLine());

            if (result == CommandManager.EXECUTE_EXIT)
                break;
        }

        System.out.println("Gym Manager terminated.");
    }

    public static void log(String message) { // logging warning/error messages that can be toggled with a boolean variable
        System.out.println(message);
    }

    public static void logf(String format, Object ... args) {
        System.out.printf(format, args);
    }
}
