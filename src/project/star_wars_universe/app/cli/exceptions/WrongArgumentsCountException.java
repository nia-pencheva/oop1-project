package project.star_wars_universe.app.cli.exceptions;

public class WrongArgumentsCountException extends Exception {
    public WrongArgumentsCountException() {
        super("Wrong arguments count!");
    }
}
