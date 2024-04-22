package project.star_wars_universe.exceptions.jedi;

public class HighestRankReachedException extends Exception {
    public HighestRankReachedException() {
        super("This jedi cannot be promoted (highest rank is reached).");
    }
}
