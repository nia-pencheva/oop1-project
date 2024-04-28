package project.star_wars_universe.app.cli.commands;

import project.star_wars_universe.exceptions.cli.CommandExecutionException;

import java.util.List;

public abstract class Command {
    private int segmentsCount;

    public Command(int segmentsCount) {
        this.segmentsCount = segmentsCount;
    }

    public abstract void execute(List<String> input) throws CommandExecutionException;

    public boolean hasCorrectArgumentsCount(List<String> input) {
        return input.size() == this.segmentsCount;
    }
}
