package command;

/**
 * Represents a console command, stores basic command info
 * @author Michael Liu, Genfu Liu
 */
public class Command {

    /**
     * Brief description of the command
     */
    private final String description;

    /**
     * The main command, Ex. "Add", "Remove", "A"
     */
    private final String label;

    /**
     * Any aliases the command might have, Ex. "Add" might have aliases like "A", "Insert", or "Put"
     */
    private final String[] aliases;

    /**
     * The specific CommandExecutor that is responsible for executing the current command
     */
    private final CommandExecutor commandExecutor;

    /**
     * Constructor used to initialize all the command info
     * @param label the main command
     * @param aliases any aliases the command might have
     * @param description a brief description of the command
     * @param commandExecutor an instance of the CommandExecutor that is responsible for executing the command
     */
    public Command(String label, String[] aliases, String description, CommandExecutor commandExecutor) {
        this.label = label;
        this.aliases = aliases;
        this.description = description;
        this.commandExecutor = commandExecutor;
    }

    /**
     * Get the description for the current command
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the label for the main command
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get the command's aliases
     * @return an array of strings each one is an aliases
     */
    public String[] getAliases() {
        return aliases;
    }

    /**
     * Get the executor for the command
     * @return an instance of the command executor for this command
     */
    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }
}
