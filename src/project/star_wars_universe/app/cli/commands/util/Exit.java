package project.star_wars_universe.app.cli.commands.util;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;

import java.util.List;

public class Exit implements Command {
    @Override
    public void execute(List<String> input) throws WrongArgumentsCountException {
        if(input.size() != 1) {
            throw new WrongArgumentsCountException();
        }

        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
