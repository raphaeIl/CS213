package command.commands;

import client.GymManager;
import command.Command;
import command.CommandExecutor;
import command.CommandManager;
import datatypes.List;
import utils.Utils;

/**
 * The Executor Implementation for the "Help" command
 * (helpful command I added for fun, run "help" to see some helpful hints :)
 * @author Michael Liu, Genfu Liu
 */
public class HelpCommand implements CommandExecutor {

    /**
     * Inherited method from CommandExecutor used to execute the command
     * @param label the main command
     * @param args any arguments that the command have
     * @return if the command was successfully executed or not
     */
    @Override
    public boolean executeCommand(String label, String[] args) {
        List<Command> commands = CommandManager.getInstance().getAllCommands();

        GymManager.logf("%-20s %-50s %s\n\n", "Command", "Aliases:", "Description:");

        for (int i = 0; i < commands.size(); i++) {
            Command current = commands.get(i);
            GymManager.logf("%-20s %-50s %s\n", "<" + current.getLabel() + ">", Utils.arrayToString(current.getAliases()), current.getDescription());
        }

        return true;
    }
}
