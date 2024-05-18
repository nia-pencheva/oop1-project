package project.star_wars_universe.cli.commands.main;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.jedi.JediDoesNotExistException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.data.repository.JediRepository;
import project.star_wars_universe.data.repository.PlanetsRepository;
import project.star_wars_universe.util.comparators.jedi.JediByRankAndName;

import java.util.Comparator;
import java.util.List;

/**
 * The {@link Command} for displaying information about a jedi or a planet
 * based on the user input.
 */
public class Print implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();
    /**
     * A reference to the {@link JediRepository}.
     */
    private JediRepository jediRepository = JediRepository.getInstance();
    /**
     * A reference to the {@link PlanetsRepository}.
     */
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();

    /**
     * First the method checks whether a {@link Jedi} with the specified name exists in the
     * {@link JediRepository} and if yes displays the information about them using {@link Jedi#toString()} implicitly.
     * If no such jedi exists, a {@link JediDoesNotExistException} is thrown. In its catch block the method checks
     * whether a {@link Planet} with the specified name exists in the {@link PlanetsRepository} and if yes its
     * {@link Planet#jediPopulation} gets sorted by rank (ascending) and name (lexicographically, ascending) by passing
     * the {@link JediByRankAndName} comparator class to the {@link Planet#sortJediPopulation(Comparator)} method.
     * Then the planet information is displayed using {@link Planet#toString()} implicitly. If no such planet exists,
     * a {@link PlanetDoesNotExistException} is thrown and an error message is displayed.
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

        String name = input.get(1);

        try {
            Jedi jedi = jediRepository.getJediByName(name);
            System.out.println(jedi);
        }
        catch(JediDoesNotExistException ex) {
            try {
                Planet planet = planetsRepository.getPlanetByName(name);
                planet.sortJediPopulation(new JediByRankAndName());
                System.out.println(planet);
            }
            catch (PlanetDoesNotExistException exception) {
                System.out.println("There is no jedi or planet with that name!");
            }
        }
    }
}
