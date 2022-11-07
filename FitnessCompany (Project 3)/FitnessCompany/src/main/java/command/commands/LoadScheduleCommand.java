package command.commands;

import command.CommandExecutor;
import managers.DatabaseManager;

/**
 * The Executor Implementation for the "LoadSchedule (LS)" command
 * @author Michael Liu, Genfu Liu
 */
public class LoadScheduleCommand implements CommandExecutor {

    /**
     * Inherited method from CommandExecutor used to execute the command
     * @param label the main command
     * @param args any arguments that the command have
     * @return if the command was successfully executed or not
     */
    @Override
    public boolean executeCommand(String label, String[] args) {
        DatabaseManager.getInstance().getClassDatabase().loadSchedule("FitnessCompany (Project 2)/classSchedule.txt");

        return true;
    }
}
