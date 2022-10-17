package command.commands;

import command.CommandExecutor;
import core.ClassDatabase;
import managers.DatabaseManager;

/**
 * The Executor Implementation for the "DisplaySchedule (S)" command
 * @author Michael Liu, Genfu Liu
 */
public class DisplayScheduleCommand implements CommandExecutor {

    /**
     * Inherited method from CommandExecutor used to execute the command
     * @param label the main command
     * @param args any arguments that the command have
     * @return if the command was successfully executed or not
     */
    @Override
    public boolean executeCommand(String label, String[] args) {
        ClassDatabase classDatabase = DatabaseManager.getInstance().getClassDatabase();

        if (classDatabase == null)
            return false;

        classDatabase.displaySchedule();

        return true;
    }
}
