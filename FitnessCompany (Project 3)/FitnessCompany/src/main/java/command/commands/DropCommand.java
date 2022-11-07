package command.commands;

import client.GymManager;
import command.CommandExecutor;
import core.ClassDatabase;
import core.MemberDatabase;
import core.entity.Member;
import datatypes.Date;
import core.FitnessClass;
import managers.DatabaseManager;
import utils.MemberValidator;

/**
 * The Executor Implementation for the "Drop" command
 * @author Michael Liu, Genfu Liu
 */
public class DropCommand implements CommandExecutor {

    /**
     * Inherited method from CommandExecutor used to execute the command
     * @param label the main command
     * @param args any arguments that the command have
     * @return if the command was successfully executed or not
     */
    @Override
    public boolean executeCommand(String label, String[] args) {
        MemberDatabase memberDatabase = DatabaseManager.getInstance().getMemberDatabase();
        ClassDatabase classDatabase = DatabaseManager.getInstance().getClassDatabase();

        Member target = memberDatabase.get(new Member(args[4], args[5], new Date(args[6]), null, null));

        if (label.equals("D") || label.equals("Drop")) {
            if (!MemberValidator.validateMemberDrop(classDatabase, memberDatabase, args[1], args[2], args[3], args[4], args[5], args[6]))
                return false;

            if (classDatabase.drop(new FitnessClass(args[1], args[2], "", args[3]), target))
                GymManager.logf("%s %s done with the class.\n", target.getFname(), target.getLname());

            return true;
        }

        target = memberDatabase.get(new Member(args[4], args[5], new Date(args[6]), null, null));

        if (!MemberValidator.validateGuestDrop(classDatabase, memberDatabase, args[1], args[2], args[3], args[4], args[5], args[6]))
            return false;

        if (classDatabase.dropGuest(new FitnessClass(args[1], args[2], "", args[3]), target))
            GymManager.logf("%s %s Guest done with the class.\n", target.getFname(), target.getLname());

        return true;
    }
}
