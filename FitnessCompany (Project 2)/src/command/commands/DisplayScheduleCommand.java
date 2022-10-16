package command.commands;

import command.CommandExecutor;
import core.ClassDatabase;
import managers.DatabaseManager;

public class DisplayScheduleCommand implements CommandExecutor {
    @Override
    public boolean executeCommand(String label, String[] args) {
        ClassDatabase classDatabase = DatabaseManager.getInstance().getClassDatabase();

        if (classDatabase == null)
            return false;

        classDatabase.displaySchedule();

        return true;
    }
}
