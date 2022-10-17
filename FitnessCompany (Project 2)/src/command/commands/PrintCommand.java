package command.commands;

import command.CommandExecutor;
import core.MemberDatabase;
import managers.DatabaseManager;

/**
 * The Executor Implementation for the "Print (P, PF...)" command
 * @author Michael Liu, Genfu Liu
 */

public class PrintCommand implements CommandExecutor {

    /**
     * Inherited method from CommandExecutor used to execute the command
     * @param label the main command
     * @param args any arguments that the command have
     * @return if the command was successfully executed or not
     */
    @Override
    public boolean executeCommand(String label, String[] args) {
        MemberDatabase memberDatabase = DatabaseManager.getInstance().getMemberDatabase();

        switch (label) {
            case "P":
                memberDatabase.print();

                break;
            case "PC":
                memberDatabase.printByCounty();

                break;
            case "PN":
                memberDatabase.printByName();

                break;
            case "PD":
                memberDatabase.printByExpirationDate();

                break;
            case "PF":
                memberDatabase.printWithMembershipFees();
                break;
        }

        return true;
    }
}
