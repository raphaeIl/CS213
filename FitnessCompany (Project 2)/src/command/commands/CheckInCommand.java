package command.commands;

import client.GymManager;
import command.CommandExecutor;
import core.ClassDatabase;
import core.MemberDatabase;
import core.entity.Member;
import datatypes.Date;
import datatypes.FitnessClass;
import managers.DatabaseManager;
import utils.MemberValidator;

/**
 * The Executor Implementation for the "CheckIn" command
 * @author Michael Liu, Genfu Liu
 */
public class CheckInCommand implements CommandExecutor {

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

        Member target;
        FitnessClass fitnessClass;

        if (label.equals("C") || label.equals("CheckIn")) {
            target = memberDatabase.get(new Member(args[4], args[5], new Date(args[6]), null, null));
            fitnessClass = classDatabase.getFitnessClass(new FitnessClass(args[1], args[2], "", args[3]));

            if (!MemberValidator.validateMemberCheckIn(classDatabase, memberDatabase, args[1], args[2], args[3], args[4], args[5], args[6]))
                return false;

            if (classDatabase.checkIn(fitnessClass, target))
                GymManager.logf("%s %s checked in ", target.getFname(), target.getLname(), fitnessClass);

            fitnessClass.displaySchedule();

            return true;
        }

        target = memberDatabase.get(new Member(args[4], args[5], new Date(args[6]), null, null));

        if (!MemberValidator.validateGuestCheckIn(classDatabase, memberDatabase, args[1], args[2], args[3], args[4], args[5], args[6]))
            return false;

        fitnessClass = classDatabase.getFitnessClass(new FitnessClass(args[1], args[2], "", args[3]));

        if (classDatabase.checkInGuest(new FitnessClass(args[1], args[2], "", args[3]), target))
            GymManager.logf("%s %s (guest) checked in ", target.getFname(), target.getLname(), fitnessClass);

        fitnessClass.displaySchedule();
        GymManager.log("");

        return true;
    }
}
