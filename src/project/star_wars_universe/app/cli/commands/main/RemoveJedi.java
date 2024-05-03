package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.jedi.JediDoesNotExistException;
import project.star_wars_universe.exceptions.planets.JediDoesNotExistOnThisPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.repository.JediRepository;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.List;

public class RemoveJedi implements Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();
    private JediRepository jediRepository = JediRepository.getInstance();
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();

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

            PlanetsRepository.getInstance().getPlanetByName(planetName).removeJedi(jedi);
            jediRepository.remove(jedi);
            System.out.println("Jedi " + name + " was successfully removed!");
        }
        catch(PlanetDoesNotExistException | JediDoesNotExistOnThisPlanetException | JediDoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
