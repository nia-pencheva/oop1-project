package project.star_wars_universe.exceptions.planets;

public class JediDoesNotExistOnThisPlanetException extends Exception {
    public JediDoesNotExistOnThisPlanetException() {
        super("Jedi with that name does NOT exist on this planet!");
    }
}
