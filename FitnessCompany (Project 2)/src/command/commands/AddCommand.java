package command.commands;

import client.GymManager;
import command.CommandExecutor;
import core.MemberDatabase;
import core.entity.Member;
import datatypes.MemberType;
import managers.DatabaseManager;
import utils.MemberValidator;

public class AddCommand implements CommandExecutor {

    @Override
    public boolean executeCommand(String label, String[] args) {
        if (args.length <= 1)
            return false;

        MemberDatabase memberDatabase = DatabaseManager.getInstance().getMemberDatabase();
        MemberType memberType = null;

        switch (label) {
            case "A": memberType = MemberType.Standard; break;
            case "AF": memberType = MemberType.Family; break;
            case "AP": memberType = MemberType.Premium; break;
        }

        Member newMember = MemberValidator.validateAndCreateMember(args[1], args[2], args[3], null, args[4], memberType);

        if (newMember == null || !MemberValidator.validateMemberDatabaseInsertion(memberDatabase, newMember))
            return true;

        if (memberDatabase.add(newMember))
            GymManager.logf("%s %s added.\n", newMember.getFname(), newMember.getLname());

        return true;
    }
}
