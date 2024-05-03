package project.star_wars_universe.app.cli.commands.util;

import project.star_wars_universe.app.cli.enums.Commands;
import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;

import java.util.List;

public class Help implements Command {
    @Override
    public void execute(List<String> input) throws WrongArgumentsCountException {
        if(input.size() != 1) {
            throw new WrongArgumentsCountException();
        }

        System.out.println("The following commands are supported:");
        for(Commands command : Commands.values()) {
            System.out.println("* " + command.getSyntax());
            System.out.println("\t- " + command.getDescription());
            System.out.println();
        }
    }
}
