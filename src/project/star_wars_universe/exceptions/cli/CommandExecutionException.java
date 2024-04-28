package project.star_wars_universe.exceptions.cli;

public class CommandExecutionException extends Exception {
    public CommandExecutionException() {
        super("Command execution failed!");
    }

    public CommandExecutionException(String message) {
        super(message);
    }
}
