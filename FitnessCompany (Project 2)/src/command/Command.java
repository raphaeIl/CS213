package command;

public class Command {

    private String description;

    private String label;

    private String[] aliases;

    private CommandExecutor commandHandler;

    public Command(String label, String[] aliases, String description, CommandExecutor commandHandler) {
        this.description = description;
        this.label = label;
        this.aliases = aliases;
        this.commandHandler = commandHandler;
    }

    public String getDescription() {
        return description;
    }

    public String getLabel() {
        return label;
    }

    public String[] getAliases() {
        return aliases;
    }

    public CommandExecutor getCommandHandler() {
        return commandHandler;
    }


}
