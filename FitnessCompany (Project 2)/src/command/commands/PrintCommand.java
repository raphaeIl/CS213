package command.commands;

import command.CommandExecutor;
import core.MemberDatabase;
import managers.DatabaseManager;

public class PrintCommand implements CommandExecutor {
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
