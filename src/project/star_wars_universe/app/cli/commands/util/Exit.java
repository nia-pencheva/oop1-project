package project.star_wars_universe.app.cli.commands.util;

import project.star_wars_universe.app.cli.commands.Command;

public class Exit extends Command {

    public Exit() {
        super(1);
    }

    @Override
    public void execute() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
