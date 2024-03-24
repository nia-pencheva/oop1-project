package project.star_wars_universe.app.cli.commands;

import project.star_wars_universe.app.cli.enums.Commands;

public class Help extends Command {
    public Help() {
        super(1);
    }

    @Override
    public void execute() throws Exception {
        System.out.println("The following commands are supported:");

        for(Commands command : Commands.values()) {
            System.out.println("* " + command.getSyntax());
            System.out.println("\t- " + command.getDescription());
            System.out.println();
        }
    }
}
