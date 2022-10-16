package command.commands;

import client.GymManager;
import command.Command;
import command.CommandExecutor;
import command.CommandManager;
import datatypes.List;
import utils.Utils;

public class HelpCommand implements CommandExecutor {

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
