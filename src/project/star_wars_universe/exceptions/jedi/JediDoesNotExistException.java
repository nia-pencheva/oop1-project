package project.star_wars_universe.exceptions.jedi;

/**
 * Thrown when a {@link project.star_wars_universe.models.jedi.Jedi} is searched for in the
 * {@link project.star_wars_universe.data.repository.JediRepository} but does not exist there.
 */
public class JediDoesNotExistException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public JediDoesNotExistException() {
        super("Jedi does not exist!");
    }
}
