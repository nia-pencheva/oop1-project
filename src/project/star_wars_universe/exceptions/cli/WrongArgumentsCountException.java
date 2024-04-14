package project.star_wars_universe.exceptions.cli;

public class WrongArgumentsCountException extends Exception {
    public WrongArgumentsCountException() {
        super("Wrong arguments count!");
    }
}
