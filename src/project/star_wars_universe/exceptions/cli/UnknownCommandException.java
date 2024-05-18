package project.star_wars_universe.exceptions.cli;

/**
 * A CLI command execution exception thrown when a command which is not supported by the application is entered.
 */
public class UnknownCommandException extends CommandExecutionException {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link CommandExecutionException}'s constructor).
     */
    public UnknownCommandException() {
        super("No such command!");
    }
}
