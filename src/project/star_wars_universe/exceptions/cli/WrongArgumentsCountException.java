package project.star_wars_universe.exceptions.cli;

/**
 * A CLI command execution exception thrown when a command is entered with the wrong number of arguments.
 */
public class WrongArgumentsCountException extends CommandExecutionException {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link CommandExecutionException}'s constructor).
     */
    public WrongArgumentsCountException() {
        super("Wrong arguments count!");
    }
}
