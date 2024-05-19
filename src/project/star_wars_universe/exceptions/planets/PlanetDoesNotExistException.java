package project.star_wars_universe.exceptions.planets;

/**
 * Thrown when a {@link project.star_wars_universe.models.planets.Planet} is searched for in the
 * {@link project.star_wars_universe.data.repository.PlanetsRepository} but does not exist there.
 */
public class PlanetDoesNotExistException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public PlanetDoesNotExistException() {
        super("Planet does not exist!");
    }
}
