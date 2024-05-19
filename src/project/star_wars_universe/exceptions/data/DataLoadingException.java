package project.star_wars_universe.exceptions.data;

/**
 * Thrown when an unexpected event occurs during the loading of data
 * into the application (usually by an implementation of the {@link project.star_wars_universe.contracts.data.DataLoader}
 * interface). It holds a reference to the specific exception which was
 * thrown during the data loading process.
 *
 */
public class DataLoadingException extends Exception {
    /**
     * A reference to the specific exception thrown
     * during the data loading process.
     */
    private Exception exception;

    /**
     * Initializes the exception instance with the specific exception
     * thrown during the loading process.
     * @param exception the specific exception thrown during the data loading process.
     */
    public DataLoadingException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Gets the specific exception thrown during the data loading process.
     * @return the specific exception thrown during the data loading process
     */
    public Exception getException() {
        return exception;
    }
}
