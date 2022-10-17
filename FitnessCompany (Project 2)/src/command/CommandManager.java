package command;

import client.GymManager;
import command.commands.*;
import datatypes.List;
import utils.Utils;

/**
 * This singleton class is mainly responsible for managing all commands functionality such as,
 * registering, handling and invoking commands.
 * Uses a command pattern inspired by some games that have console features
 * @author Michael Liu, Genfu Liu
 */
public class CommandManager {

    // region Singleton
    /**
     * This class implements the Singleton pattern for easier access of this class
     * throughout the entire program as well as limiting multiple instance of it to be
     * created since this application will most likely only need 1 command manager.
     */
    private static CommandManager instance = null;

    /**
     * Static method to get the single instance of this class
     * @return the single instance of this class
     */
    public static CommandManager getInstance() {
        if (instance == null)
            instance = new CommandManager();

        return instance;
    }
    // endregion

    public static final int EXECUTE_SUCCESS = 0;
    public static final int EXECUTE_EXIT = 1;
    public static final int EXECUTE_ERROR = -1;

    /**
     * A list (custom class, not java's list) of all the registered commands
     */
    private List<Command> commands; // using our custom List, not java's arraylist

    /**
     * A private constructor that is used to register all the commands
     */
    private CommandManager() {
        commands = new List<>();

        this.registerCommand("Add", new String[] { "A", "AF", "AP" }, "Adds a member", new AddCommand());
        this.registerCommand("CheckIn", new String[] { "C", "CG", "CheckInGuest" }, "Checks in a member", new CheckInCommand());
        this.registerCommand("DisplaySchedule", new String[] { "S" }, "Display the fitness class schedule.", new DisplayScheduleCommand());
        this.registerCommand("D", new String[] { "DG" }, "Checks out a member", new DropCommand());
        this.registerCommand("LoadSchedule", new String[] { "LS" }, "Loads the fitness class schedule from a file to the class schedule in the software system", new LoadScheduleCommand());
        this.registerCommand("LoadMembers", new String[] { "LM" }, "Loads a list of members from a file to the member database", new LoadMembersCommand());
        this.registerCommand("Print", new String[] { "P", "PC", "PN", "PD", "PF" }, "Display the list of members in the database without sorting, sorting by county names/zip codes, last name/firstname, expiration date, or membership fees", new PrintCommand());
        this.registerCommand("Remove", new String[] { "R" }, "Cancels a membership and remove the specified member from the database", new RemoveCommand());
        this.registerCommand("Help", new String[] { "help" }, "Displays help information on all commands.", new HelpCommand());
    }

    /**
     * This registers a command into the command list which then if the client
     * runs the command, it will invoke the commandExecutor
     * @param label the main command
     * @param aliases any aliases the command has
     * @param description a brief description of the command
     * @param commandExecutor the command executor for that command
     */
    public void registerCommand(String label, String[] aliases, String description, CommandExecutor commandExecutor) {
        commands.add(new Command(label, aliases, description, commandExecutor));
    }

    /**
     * Handles and invokes a command that the client runs
     * @param rawMessage the raw line of message that the client inputs,
     *                   which will be parsed.
     * @return the execute status of invoking the command
     */
    public int invoke(String rawMessage) {
        String[] args = rawMessage.split(" ");

        if (args.length <= 0 || args[0].isEmpty() || args[0].isBlank())
            return EXECUTE_ERROR;

        String label = args[0];

        if (label.equals("Q")) // special command for Q
            return EXECUTE_EXIT;

        CommandExecutor handler = this.getExecutor(label);

        if (handler == null) {
            GymManager.log(label + " is an invalid command!");

            return EXECUTE_ERROR;
        }

        handler.executeCommand(label, args);

        return EXECUTE_SUCCESS;
    }

    /**
     * Gets a command's executor from it's name
     * @param label the main command's name
     * @return the command's executor
     */
    public CommandExecutor getExecutor(String label) {
        CommandExecutor executor = null;

        for (int i = 0; i < commands.size(); i++) {
            Command cmd = (Command) commands.asArray()[i];
            // a command or any of its aliases are the same command, so they have the same executor
            if (cmd.getLabel().equals(label) || Utils.arrayContains(cmd.getAliases(), label))
                executor = cmd.getCommandExecutor();
        }

        return executor;
    }

    /**
     * Gets all the available commands
     * @return a list (custom) representing all the commands
     */
    public List<Command> getAllCommands() {
        return commands;
    }

}
