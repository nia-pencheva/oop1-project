package project.star_wars_universe.exceptions.jedi;

/**
 * Thrown when the lowest rank is reached for a {@link project.star_wars_universe.models.jedi.Jedi}.
 */
public class LowestRankReachedException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public LowestRankReachedException() {
        super("This jedi cannot be demoted (lowest rank is reached).");
    }
}
