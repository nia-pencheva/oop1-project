package project.star_wars_universe.exceptions.util;

public class ParsingFailureException extends Exception {
    private Exception exception;

    public ParsingFailureException() {}

    public ParsingFailureException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
