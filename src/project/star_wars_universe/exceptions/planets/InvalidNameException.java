package project.star_wars_universe.exceptions.planets;

/**
 * Thrown when an invalid value for {@link project.star_wars_universe.models.planets.Planet#name} is attempted to be set.
 */
public class InvalidNameException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public InvalidNameException() {
        super("Invalid value for planet name!");
    }
}
