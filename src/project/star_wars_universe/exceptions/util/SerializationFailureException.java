package project.star_wars_universe.exceptions.util;

public class SerializationFailureException extends Exception {
    private Exception exception;

    public SerializationFailureException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
