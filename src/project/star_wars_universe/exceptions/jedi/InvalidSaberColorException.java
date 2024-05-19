package project.star_wars_universe.exceptions.jedi;

/**
 * Thrown when an invalid value for {@link project.star_wars_universe.models.jedi.Jedi#saberColor} is attempted to be set.
 */
public class InvalidSaberColorException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public InvalidSaberColorException() {
        super("Invalid saber color for jedi!");
    }
}
