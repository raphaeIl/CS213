package command.commands;

import command.CommandExecutor;
import managers.DatabaseManager;

public class LoadMembersCommand implements CommandExecutor {
    @Override
    public boolean executeCommand(String label, String[] args) {
        DatabaseManager.getInstance().getMemberDatabase().loadMembers("FitnessCompany (Project 2)/memberList.txt");

        return true;
    }
}
