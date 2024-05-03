package project.star_wars_universe.exceptions.cli;

public class WrongArgumentsCountException extends CommandExecutionException {
    public WrongArgumentsCountException() {
        super("Wrong arguments count!");
    }
}
