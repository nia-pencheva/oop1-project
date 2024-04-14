package project.star_wars_universe.exceptions.planets;

public class JediExistsOnThisPlanetException extends Exception {
    public JediExistsOnThisPlanetException() {
        super("Jedi already exists on this planet!");
    }
}
