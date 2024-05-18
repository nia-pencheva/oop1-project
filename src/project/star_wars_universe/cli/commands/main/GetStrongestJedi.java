package project.star_wars_universe.cli.commands.main;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.repository.PlanetsRepository;
import project.star_wars_universe.services.JediStatisticsService;

import java.util.List;

/**
 * The {@link Command} for displaying the strongest jedi on a specified planet.
 */
public class GetStrongestJedi implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();
    /**
     * A reference to the {@link PlanetsRepository}.
     */
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();

    /**
     * Gets the specified planet from the {@link PlanetsRepository}, gets the
     * strongest jedi on that planet via the {@link JediStatisticsService#getStrongestJediOnPlanet(Planet)}
     * method and displays the information about them using {@link Jedi#toString()} implicitly.
     * If the specified planet does not exist ({@link PlanetDoesNotExistException}), an error message is displayed.
     * @param input the user input.
     * @throws NoFileOpenedException if a file is not opened.
     * @throws WrongArgumentsCountException if the arguments count is incorrect.
     */
    @Override
    public void execute(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        if(input.size() != 2) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            String planetName = input.get(1);
            Planet planet = planetsRepository.getPlanetByName(planetName);
            Jedi strongestJedi = JediStatisticsService.getStrongestJediOnPlanet(planet);
            System.out.println("The strongest Jedi on " + planetName + " is:");
            System.out.println(strongestJedi);
        }
        catch(PlanetDoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
