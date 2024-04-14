package project.star_wars_universe.app.cli.commands;

import java.util.List;

public abstract class Command {
    private int segmentsCount;

    public Command(int segmentsCount) {
        this.segmentsCount = segmentsCount;
    }

    public abstract void execute() throws Exception;

    protected boolean hasCorrectArgumentsCount(List<String> input) {
        return input.size() == this.segmentsCount;
    }
}
