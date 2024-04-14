package project.star_wars_universe.exceptions.jedi;

public class InvalidPowerException extends Exception {
    public InvalidPowerException() {
        super("Invalid value for power!");
    }
}
