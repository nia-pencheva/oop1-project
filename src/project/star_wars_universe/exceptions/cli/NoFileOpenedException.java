package project.star_wars_universe.exceptions.cli;

public class NoFileOpenedException extends Exception {
    public NoFileOpenedException() {
        super("No file opened (type \"open <file>\" to open a file)");
    }
}
