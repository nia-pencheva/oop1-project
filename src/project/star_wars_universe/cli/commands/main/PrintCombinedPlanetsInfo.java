package project.star_wars_universe.cli.commands.main;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.UnknownCommandException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.controllers.JediController;

import java.util.List;
import java.util.Set;

/**
 * The {@link Command} for displaying information about the jedi populating
 * two planets in a lexicographical order (ascending).
 */
public class PrintCombinedPlanetsInfo implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();
    /**
     * A reference to the {@link PlanetsRepository}.
     */
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();
    /**
     * A reference to the first {@link Planet}. It is immutable, because each instance of
     * the PrintCombinedPlanetsInfo command must be associated with only one planet.
     * @see project.star_wars_universe.cli.commands.CommandsList#onPlanetAdded(Planet)
     */
    private final Planet planet1;

    /**
     * When the command is created, it must be associated with a given {@link Planet}.
     * @param planet1 the {@link Planet} associated with that instance of the command.
     * @see project.star_wars_universe.cli.commands.CommandsList#onPlanetAdded(Planet)
     */
    public PrintCombinedPlanetsInfo(Planet planet1) {
        this.planet1 = planet1;
    }

    /**
     * Gets the second {@link Planet} by name from the {@link PlanetsRepository} and, using the
     * {@link JediController#getCombinedJedi(Planet, Planet)} method, gets a <code>Set</code> of
     * the {@link Jedi} from both planets' {@link Planet#jediPopulation}s sorted by name lexicographically (ascending).
     * Finally, it displays them using {@link Jedi#toString()} implicitly.
     * @param input the user input.
     * @throws NoFileOpenedException if a file is not opened.
     * @throws UnknownCommandException if the format of the arguments is incorrect (two planets names separated by a "+" sign).
     */
    @Override
    public void execute(List<String> input) throws NoFileOpenedException, UnknownCommandException {
        if(input.size() != 3 || !input.get(1).equals("+")) {
            throw new UnknownCommandException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            Planet planet2 = planetsRepository.getPlanetByName(input.get(2));
            Set<Jedi> combinedJedi = JediController.getCombinedJedi(planet1, planet2);

            System.out.println("The jedi populating " + planet1.getName() + " and " + planet2.getName() + " are:");
            for(Jedi jedi : combinedJedi) {
                System.out.println();
                System.out.println(jedi);
            }
        }
        catch(PlanetDoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
