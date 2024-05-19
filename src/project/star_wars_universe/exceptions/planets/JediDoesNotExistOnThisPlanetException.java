package project.star_wars_universe.exceptions.planets;

/**
 * Thrown when a {@link project.star_wars_universe.models.jedi.Jedi} is searched for in the {@link project.star_wars_universe.models.planets.Planet#jediPopulation}
 * of a {@link project.star_wars_universe.models.planets.Planet} but does not exist there.
 */
public class JediDoesNotExistOnThisPlanetException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public JediDoesNotExistOnThisPlanetException() {
        super("Jedi with that name does NOT exist on this planet!");
    }
}
