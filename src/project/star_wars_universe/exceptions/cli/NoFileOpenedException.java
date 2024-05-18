package project.star_wars_universe.exceptions.cli;

/**
 * A CLI command execution exception thrown when there is no file opened inside the application.
 */
public class NoFileOpenedException extends CommandExecutionException {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link CommandExecutionException}'s constructor).
     */
    public NoFileOpenedException() {
        super("No file opened (type \"open <file>\" to open a file)");
    }
}
