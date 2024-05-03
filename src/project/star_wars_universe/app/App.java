package project.star_wars_universe.app;

import project.star_wars_universe.app.cli.CLI;
import project.star_wars_universe.app.cli.commands.CommandsList;
import project.star_wars_universe.repository.PlanetsRepository;

public class App {
    public void start() throws Exception {
        PlanetsRepository.getInstance().addObserver(CommandsList.getInstance());
        CLI.start();
    }
}
