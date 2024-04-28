package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.planets.PlanetAlreadyExistsException;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.List;

public class AddPlanet extends Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    public AddPlanet()  {
        super(2);
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException {
        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            PlanetsRepository.getInstance().add(new Planet(input.get(1)));
            System.out.println("Planet " + input.get(1) + " was successfully added!");
        }
        catch(PlanetAlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
