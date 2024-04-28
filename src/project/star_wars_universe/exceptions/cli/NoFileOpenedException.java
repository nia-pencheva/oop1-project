package project.star_wars_universe.exceptions.cli;

public class NoFileOpenedException extends CommandExecutionException {
    public NoFileOpenedException() {
        super("No file opened (type \"open <file>\" to open a file)");
    }
}
