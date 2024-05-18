package project.star_wars_universe.cli.commands.main;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.jedi.*;
import project.star_wars_universe.exceptions.planets.JediDoesNotExistOnThisPlanetException;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.exceptions.util.ParsingFailureException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.repository.JediRepository;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.List;

/**
 * A {@link Command} for removing a jedi.
 */
public class RemoveJedi implements Command {
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
     * Gets the specified {@link Jedi} from the {@link JediRepository}, finds the specified {@link project.star_wars_universe.models.planets.Planet}
     * from the {@link PlanetsRepository}, removes the jedi from it, removes the jedi from the {@link JediRepository} and displays a message
     * if the operation was successful. If the specified planet does not exist ({@link PlanetDoesNotExistException}), the jedi does not exist
     * on the specified planet ({@link JediDoesNotExistOnThisPlanetException}) or the jedi does not exist in the {@link JediRepository} ({@link JediDoesNotExistException}),
     * an appropriate error message is displayed.
     * @param input the user input.
     * @throws NoFileOpenedException if a file is not opened.
     * @throws WrongArgumentsCountException if the arguments count is incorrect.
     */
    @Override
    public void execute(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        if(input.size() != 3) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            String name = input.get(1);
            String planetName = input.get(2);

            Jedi jedi = jediRepository.getJediByName(name);
            planetsRepository.getPlanetByName(planetName).removeJedi(jedi);
            jediRepository.remove(jedi);
            System.out.println("Jedi " + name + " was successfully removed!");
        }
        catch(PlanetDoesNotExistException | JediDoesNotExistOnThisPlanetException | JediDoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
