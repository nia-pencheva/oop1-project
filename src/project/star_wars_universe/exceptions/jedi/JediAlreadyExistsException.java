package project.star_wars_universe.exceptions.jedi;

public class JediAlreadyExistsException extends Exception {
    public JediAlreadyExistsException() {
        super("Jedi with that name already exists!");
    }
}
