package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.jedi.InvalidRankException;
import project.star_wars_universe.exceptions.jedi_statistics.NoJediOfThisRankOnPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.services.JediStatisticsService;

import java.util.List;

public class GetYoungestJedi extends Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    public GetYoungestJedi()  {
        super(3);
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException {
        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            String planetName = input.get(1);
            String rankName = input.get(2);

            Planet planet = PlanetsRepository.getInstance().getPlanetByName(planetName);
            Rank rank = Rank.getValue(rankName);
            Jedi youngestJedi = JediStatisticsService.getYoungestJediOfRankOnPlanet(rank, planet);
            System.out.println("The youngest jedi of rank " + rank.getDisplayName() + " on planet " + planet.getName() + " is: ");
            System.out.println(youngestJedi.toString());
        }
        catch(PlanetDoesNotExistException | InvalidRankException | NoJediOfThisRankOnPlanetException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
