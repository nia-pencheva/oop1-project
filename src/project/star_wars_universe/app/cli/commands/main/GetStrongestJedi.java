package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.services.JediStatisticsService;

import java.util.List;

public class GetStrongestJedi extends Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    public GetStrongestJedi() {
        super(2);
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException {
        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            String planetName = input.get(1);
            Planet planet = PlanetsRepository.getInstance().getPlanetByName(planetName);
            Jedi strongestJedi = JediStatisticsService.getStrongestJediOnPlanet(planet);
            System.out.println("The strongest Jedi on " + planetName + " is:");
            System.out.println(strongestJedi.toString());
        }
        catch(PlanetDoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
