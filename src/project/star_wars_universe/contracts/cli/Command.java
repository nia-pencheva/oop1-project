package project.star_wars_universe.contracts.cli;

import project.star_wars_universe.exceptions.cli.CommandExecutionException;

import java.util.List;

public interface Command {
    void execute(List<String> input) throws CommandExecutionException;
}
