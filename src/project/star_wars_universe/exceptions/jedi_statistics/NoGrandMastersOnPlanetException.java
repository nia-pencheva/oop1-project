package project.star_wars_universe.exceptions.jedi_statistics;

/**
 * Thrown when a collection of the Grand Masters populating a {@link project.star_wars_universe.models.planets.Planet}
 * should be retrieved, but no jedi of that rank exist on the given planet.
 */
public class NoGrandMastersOnPlanetException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public NoGrandMastersOnPlanetException() {
        super("There are no Grand Master jedi on this planet!");
    }
}
