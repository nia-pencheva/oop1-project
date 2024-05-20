package project.star_wars_universe.exceptions.cli;

import java.util.List;

/**
 * Thrown when an unexpected event occurs during the execution
 * of a CLI command. This class can be used on its own, but it
 * is also extended by all other classes inside the {@link project.star_wars_universe.exceptions.cli}
 * package. This is implemented in such a way because it would be meaningless and even wrong
 * for the {@link project.star_wars_universe.cli.commands.Command#execute(List)} method
 * to throw a long list of exceptions, some of which might never
 * be thrown by a particular {@link project.star_wars_universe.cli.commands.Command} implementation.
 * Instead, only this exception is thrown by the {@link project.star_wars_universe.cli.commands.Command#execute(List)} method,
 * and in a concrete {@link project.star_wars_universe.cli.commands.Command}
 * implementation there could be specific exceptions signified.
 */
public class CommandExecutionException extends Exception {
    /**
     * Initializes the exception instance with a default error message.
     */
    public CommandExecutionException() {
        super("Command execution failed!");
    }

    /**
     * Initializes the exception instance with a provided error message.
     * @param message the error message.
     */
    public CommandExecutionException(String message) {
        super(message);
    }
}
