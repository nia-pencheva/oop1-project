package project.star_wars_universe.cli.commands.util;

import project.star_wars_universe.cli.enums.Commands;
import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;

import java.util.List;

/**
 * The {@link Command} for displaying all available commands in the application
 */
public class Help implements Command {
    /**
     * Displays all commands supported by the application. It extracts them from
     * the {@link Commands} enum and displays their syntax and description.
     * @param input the user input
     * @throws WrongArgumentsCountException if the arguments count is incorrect
     */
    @Override
    public void execute(List<String> input) throws WrongArgumentsCountException {
        if(input.size() != 1) {
            throw new WrongArgumentsCountException();
        }

        System.out.println("The following commands are supported:");
        for(Commands command : Commands.values()) {
            System.out.println("* " + command.getSyntax());
            System.out.println("\t- " + command.getDescription());
        }
    }
}
