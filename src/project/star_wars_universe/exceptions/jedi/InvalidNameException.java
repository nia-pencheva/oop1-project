package project.star_wars_universe.exceptions.jedi;

public class InvalidNameException extends Exception {
    public InvalidNameException() {
        super("Invalid value for jedi name!");
    }
}
