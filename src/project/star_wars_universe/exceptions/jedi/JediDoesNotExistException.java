package project.star_wars_universe.exceptions.jedi;

public class JediDoesNotExistException extends Exception {
    public JediDoesNotExistException() {
        super("Jedi does not exist!");
    }
}
