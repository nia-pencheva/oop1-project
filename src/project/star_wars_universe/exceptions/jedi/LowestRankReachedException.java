package project.star_wars_universe.exceptions.jedi;

public class LowestRankReachedException extends Exception {
    public LowestRankReachedException() {
        super("This jedi cannot be demoted (lowest rank is reached).");
    }
}
