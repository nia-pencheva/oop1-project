package project.star_wars_universe.exceptions.jedi;

/**
 * Thrown when the highest rank is reached for a {@link project.star_wars_universe.models.jedi.Jedi}.
 */
public class HighestRankReachedException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public HighestRankReachedException() {
        super("This jedi cannot be promoted (highest rank is reached).");
    }
}
