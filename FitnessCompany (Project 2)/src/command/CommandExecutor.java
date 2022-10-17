package command;

/**
 * This interface provides the template for all classes that is responsible for executing a specific command,
 * achieves abstraction and all subclasses will be able to execute a command.
 * @author Michael Liu, Genfu Liu
 */
public interface CommandExecutor {

    /**
     * All CommandExecutors will have the functionality to execute a command
     * @param label the main command
     * @param args any arguments that the command have
     * @return if the command was successfully executed or not
     */
    boolean executeCommand(String label, String[] args);

}
