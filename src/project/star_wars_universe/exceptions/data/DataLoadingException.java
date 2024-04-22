package project.star_wars_universe.exceptions.data;

public class DataLoadingException extends Exception {
    private Exception exception;

    public DataLoadingException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
