package project.star_wars_universe.cli.commands.util;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;

import java.util.List;

/**
 * The {@link Command} command for exiting the application.
 */
public class Exit implements Command {
    /**
     * Displays an exit message and kills the program.
     * @param input the user input
     * @throws WrongArgumentsCountException if the arguments count is incorrect
     */
    @Override
    public void execute(List<String> input) throws WrongArgumentsCountException {
        if(input.size() != 1) {
            throw new WrongArgumentsCountException();
        }

        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
