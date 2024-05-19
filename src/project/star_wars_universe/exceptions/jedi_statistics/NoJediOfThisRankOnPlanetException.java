package project.star_wars_universe.exceptions.jedi_statistics;

/**
 * Thrown when a collection of the jedi of a particular rank populating a {@link project.star_wars_universe.models.planets.Planet}
 * should be retrieved, but no jedi of that rank exist on the given planet.
 */
public class NoJediOfThisRankOnPlanetException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public NoJediOfThisRankOnPlanetException() {
        super("There are no jedi with this rank on this planet!");
    }
}
