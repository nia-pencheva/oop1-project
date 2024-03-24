package project.star_wars_universe.app.cli.commands;

public class Exit extends Command {

    public Exit() {
        super(1);
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
