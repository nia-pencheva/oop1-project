package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.planets.PlanetAlreadyExistsException;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.List;

public class AddPlanet implements Command {
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
            Planet newPlanet = new Planet(planetName);

            planetsRepository.add(newPlanet);
            System.out.println("Planet " + input.get(1) + " was successfully added!");
        }
        catch(PlanetAlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
