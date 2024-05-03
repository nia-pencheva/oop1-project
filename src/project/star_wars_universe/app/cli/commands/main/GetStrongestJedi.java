package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.controllers.JediController;

import java.util.List;

public class GetStrongestJedi implements Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();

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
            Jedi strongestJedi = JediController.getStrongestJediOnPlanet(planet);
            System.out.println("The strongest Jedi on " + planetName + " is:");
            System.out.println(strongestJedi);
        }
        catch(PlanetDoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
