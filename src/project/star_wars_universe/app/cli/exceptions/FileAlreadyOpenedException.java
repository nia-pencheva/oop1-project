package project.star_wars_universe.app.cli.exceptions;

public class FileAlreadyOpenedException extends Exception {
    public FileAlreadyOpenedException() {
        super("A file is already open (type \"close\" to close it)");
    }
}
