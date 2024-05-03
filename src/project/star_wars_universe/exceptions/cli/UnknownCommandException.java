package project.star_wars_universe.exceptions.cli;

public class UnknownCommandException extends CommandExecutionException {
    public UnknownCommandException() {
        super("No such command!");
    }
}
