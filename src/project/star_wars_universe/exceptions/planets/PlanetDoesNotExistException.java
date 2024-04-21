package project.star_wars_universe.exceptions.planets;

public class PlanetDoesNotExistException extends Exception {
    public PlanetDoesNotExistException() {
        super("Planet does not exist!");
    }
}
