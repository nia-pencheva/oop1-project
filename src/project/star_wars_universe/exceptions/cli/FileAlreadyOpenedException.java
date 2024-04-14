package project.star_wars_universe.exceptions.cli;

public class FileAlreadyOpenedException extends Exception {
    public FileAlreadyOpenedException() {
        super("A file is already open (type \"close\" to close it)");
    }
}
