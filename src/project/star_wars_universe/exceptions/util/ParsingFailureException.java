package project.star_wars_universe.exceptions.util;

/**
 * Thrown when an unexpected event occurs during a parsing process.
 * This class is used to wrap the original exception that was thrown during a parsing
 * process so that the {@link project.star_wars_universe.contracts.util.Parser} interface's
 * {@link project.star_wars_universe.contracts.util.Parser#parse(Object)} method does not have
 * to define a long list of exceptions it might potentially throw, but just a single {@link ParsingFailureException}.
 * The original exception can be retrieved using the {@link ParsingFailureException#getException()} method.
 */
public class ParsingFailureException extends Exception {
    /**
     * A reference to the original exception that was thrown during a parsing process.
     */
    private Exception exception;

    /**
     * Initializes the exception with the original exception that was thrown during a parsing process.
     * @param exception the original exception that was thrown during a parsing process.
     */
    public ParsingFailureException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Gets the original exception that was thrown during a parsing process.
     * @return the original exception that was thrown during a parsing process.
     */
    public Exception getException() {
        return exception;
    }
}
