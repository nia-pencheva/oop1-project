package project.star_wars_universe.exceptions.cli;

/**
 * A CLI command execution exception thrown when a file is already opened inside the application.
 */
public class FileAlreadyOpenedException extends CommandExecutionException {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link CommandExecutionException}'s constructor).
     */
    public FileAlreadyOpenedException() {
        super("A file is already open (type \"close\" to close it)");
    }
}
