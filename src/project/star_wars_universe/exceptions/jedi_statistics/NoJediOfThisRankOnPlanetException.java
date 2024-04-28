package project.star_wars_universe.exceptions.jedi_statistics;

public class NoJediOfThisRankOnPlanetException extends Exception {
    public NoJediOfThisRankOnPlanetException() {
        super("There are no jedi with this rank on this planet!");
    }
}
