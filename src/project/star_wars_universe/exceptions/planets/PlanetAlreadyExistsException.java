package project.star_wars_universe.exceptions.planets;

/**
 * Thrown when a {@link project.star_wars_universe.models.planets.Planet} is attempted to be added to the
 * {@link project.star_wars_universe.data.repository.PlanetsRepository} when it already exists there.
 */
public class PlanetAlreadyExistsException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public PlanetAlreadyExistsException() {
        super("Planet already exists!");
    }
}
