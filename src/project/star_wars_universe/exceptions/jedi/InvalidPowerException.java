package project.star_wars_universe.exceptions.jedi;

/**
 * Thrown when an invalid value for {@link project.star_wars_universe.models.jedi.Jedi#power} is attempted to be set.
 */
public class InvalidPowerException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public InvalidPowerException() {
        super("Invalid value for jedi's power!");
    }
}
