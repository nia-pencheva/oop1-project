package project.star_wars_universe.exceptions.planets;

/**
 * Thrown when a {@link project.star_wars_universe.models.jedi.Jedi} is attempted to be added to
 * a {@link project.star_wars_universe.models.planets.Planet}'s {@link project.star_wars_universe.models.planets.Planet#jediPopulation}
 * when it already exists.
 */
public class JediExistsOnThisPlanetException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public JediExistsOnThisPlanetException() {
        super("Jedi already exists on this planet!");
    }
}
