package project.star_wars_universe.exceptions.jedi;

/**
 * Thrown when an invalid jedi promotion or demotion multiplier is supplied.
 */
public class InvalidPromotionMultiplierException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public InvalidPromotionMultiplierException() {
        super("Multiplier must be a positive number");
    }
}
