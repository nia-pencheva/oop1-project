package project.star_wars_universe.exceptions.cli;

public class FileAlreadyOpenedException extends CommandExecutionException {
    public FileAlreadyOpenedException() {
        super("A file is already open (type \"close\" to close it)");
    }
}
