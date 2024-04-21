package project.star_wars_universe.exceptions.planets;

public class PlanetAlreadyExistsException extends Exception {
    public PlanetAlreadyExistsException() {
        super("Planet already exists!");
    }
}
