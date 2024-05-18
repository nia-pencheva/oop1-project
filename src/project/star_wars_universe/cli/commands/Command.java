package project.star_wars_universe.cli.commands;

import project.star_wars_universe.exceptions.cli.CommandExecutionException;

import java.util.List;

/**
 * Describes the contract for implementing a CLI command. The concrete classes
 * must encapsulate the logic related to executing a certain CLI command - arguments,
 * dependencies and the delegation of work to other classes of the application.
 */
public interface Command {
    /**
     * Executes a desired behaviour.
     * This behaviour can either be delegated to other classes of
     * the application or implemented inside the execute method itself.
     * @param input the user input.
     * @throws CommandExecutionException if the command execution fails.
     */
    void execute(List<String> input) throws CommandExecutionException;
}
