package command.commands;

import client.GymManager;
import command.CommandExecutor;
import core.MemberDatabase;
import core.entity.Member;
import datatypes.Date;
import managers.DatabaseManager;

/**
 * The Executor Implementation for the "Remove" command
 * @author Michael Liu, Genfu Liu
 */
public class RemoveCommand implements CommandExecutor {

    /**
     * Inherited method from CommandExecutor used to execute the command
     * @param label the main command
     * @param args any arguments that the command have
     * @return if the command was successfully executed or not
     */
    @Override
    public boolean executeCommand(String label, String[] args) {
        MemberDatabase memberDatabase = DatabaseManager.getInstance().getMemberDatabase();
        Member target = memberDatabase.get(new Member(args[1], args[2], new Date(args[3]), null, null));

        if (memberDatabase.remove(target))
            GymManager.logf("%s %s removed.\n", target.getFname(), target.getLname());
        else
            GymManager.logf("%s %s is not in the database.\n", args[1], args[2]);

        return true;
    }
}
