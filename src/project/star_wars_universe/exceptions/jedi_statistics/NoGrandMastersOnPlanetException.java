package project.star_wars_universe.exceptions.jedi_statistics;

public class NoGrandMastersOnPlanetException extends Exception {
    public NoGrandMastersOnPlanetException() {
        super("There are no Grand Master jedi on this planet!");
    }
}
