package project.star_wars_universe.exceptions.util;

import project.star_wars_universe.data.AppData;

/**
 * Thrown when an unexpected event occurs during a serialization process.
 * This class is used to wrap the original exception that was thrown during a serialization
 * process so that the {@link project.star_wars_universe.contracts.util.Serializer} interface's
 * {@link project.star_wars_universe.contracts.util.Serializer#serialize(AppData)} method does not have
 * to define a long list of exceptions it might potentially throw, but just a single {@link SerializationFailureException}.
 * The original exception can be retrieved using the {@link SerializationFailureException#getException()} method.
 */
public class SerializationFailureException extends Exception {
    /**
     * A reference to the original exception that was thrown during a serialization process.
     */
    private Exception exception;

    /**
     * Initializes the exception with the original exception that was thrown during a serialization process.
     * @param exception the original exception that was thrown during a serialization process.
     */
    public SerializationFailureException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Gets the original exception that was thrown during a serialization process.
     * @return the original exception that was thrown during a serialization process.
     */
    public Exception getException() {
        return exception;
    }
}
