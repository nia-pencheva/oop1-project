package project.star_wars_universe.exceptions.jedi;

/**
 * Thrown when an invalid value for {@link project.star_wars_universe.models.jedi.Jedi#age} is attempted to be set.
 */
public class InvalidAgeException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public InvalidAgeException() {
        super("Invalid age for jedi!");
    }
}
