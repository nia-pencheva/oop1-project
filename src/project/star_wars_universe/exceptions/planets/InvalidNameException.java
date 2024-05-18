package project.star_wars_universe.exceptions.planets;

public class InvalidNameException extends Exception {
    public InvalidNameException() {
        super("Invalid value for planet name!");
    }
}
