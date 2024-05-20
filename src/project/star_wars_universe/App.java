package project.star_wars_universe;

import project.star_wars_universe.cli.CLI;
import project.star_wars_universe.cli.commands.CommandsList;
import project.star_wars_universe.data.repository.PlanetsRepository;

/**
 * Sets up the application and starts the CLI.
 */
public class App {

    /**
     * Sets up the application and starts the CLI.
     */
    public void start() {
        initialize();
        (new CLI()).start();
    }

    /**
     * Initializes and connects different dependencies inside the project
     * (e.g. adds observers to subjects).
     */
    public void initialize() {
        PlanetsRepository.getInstance().addObserver(CommandsList.getInstance());
    }
}
