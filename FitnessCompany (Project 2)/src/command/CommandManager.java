package command;

import client.GymManager;
import command.commands.*;
import datatypes.List;
import utils.Utils;

/**
 * This class uses a command pattern that is inspired from some games
 * that I've seen in the past that has console features
 */
public class CommandManager {

    // region Singleton
    private static CommandManager instance = null;

    public static CommandManager getInstance() {
        if (instance == null)
            instance = new CommandManager();

        return instance;
    }
    // endregion

    public static final int EXECUTE_SUCCESS = 0;
    public static final int EXECUTE_EXIT = 1;
    public static final int EXECUTE_ERROR = -1;

    private List<Command> commands; // using our custom List, not java's arraylist

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

    public void registerCommand(String label, String[] aliases, String description, CommandExecutor commandExecutor) {
        commands.add(new Command(label, aliases, description, commandExecutor));
    }

    public int invoke(String rawMessage) {
        String[] args = rawMessage.split(" ");

        if (args.length <= 0 || args[0].isEmpty() || args[0].isBlank())
            return EXECUTE_ERROR;

        String label = args[0];

        if (label.equals("Q")) // special command for Q
            return EXECUTE_EXIT;

        CommandExecutor handler = this.getHandler(label);

        if (handler == null) {
            GymManager.log(label + " is an invalid command!");

            return EXECUTE_ERROR;
        }

        handler.executeCommand(label, args);

        return EXECUTE_SUCCESS;
    }

    public CommandExecutor getHandler(String label) {
        CommandExecutor handler = null;

        for (int i = 0; i < commands.size(); i++) {
            Command cmd = (Command) commands.asArray()[i];

            if (cmd.getLabel().equals(label) || Utils.arrayContains(cmd.getAliases(), label))
                handler = cmd.getCommandHandler();
        }

        return handler;
    }

    public List<Command> getAllCommands() {
        return commands;
    }

}
