package command.commands;

import command.CommandExecutor;
import managers.DatabaseManager;

public class LoadScheduleCommand implements CommandExecutor {

    @Override
    public boolean executeCommand(String label, String[] args) {
        DatabaseManager.getInstance().getClassDatabase().loadSchedule("FitnessCompany (Project 2)/classSchedule.txt");

        return true;
    }
}
