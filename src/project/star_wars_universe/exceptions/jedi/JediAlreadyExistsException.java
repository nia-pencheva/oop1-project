package project.star_wars_universe.exceptions.jedi;

/**
 * Thrown when a {@link project.star_wars_universe.models.jedi.Jedi} is attempted to be added to
 * the {@link project.star_wars_universe.data.repository.JediRepository} when it already exists there.
 */
public class JediAlreadyExistsException extends Exception {
    /**
     * Initializes the exception instance with a default error message (passed to the {@link Exception}'s constructor).
     */
    public JediAlreadyExistsException() {
        super("Jedi with that name already exists!");
    }
}
