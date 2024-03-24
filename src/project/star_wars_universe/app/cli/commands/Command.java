package project.star_wars_universe.app.cli.commands;

import project.star_wars_universe.app.cli.CLI;

import java.util.List;

public abstract class Command {
    private int segmentsCount;

    public Command(int segmentsCount) {
        this.segmentsCount = segmentsCount;
    }

    public abstract void execute() throws Exception;

    protected boolean enoughArguments(List<String> input) {
        if(input.size() < this.segmentsCount) {
            System.out.println("Not enough arguments!");
            CLI.printHelpMessage();
            return false;
        }
        return true;
    }
}
