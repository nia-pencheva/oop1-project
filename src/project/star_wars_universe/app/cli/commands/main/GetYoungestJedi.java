package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.jedi.InvalidRankException;
import project.star_wars_universe.exceptions.jedi_statistics.NoJediOfThisRankOnPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.controllers.JediController;

import java.util.List;

public class GetYoungestJedi implements Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    @Override
    public void execute(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        if(input.size() != 3) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            String planetName = input.get(1);
            String rankName = input.get(2);

            Planet planet = PlanetsRepository.getInstance().getPlanetByName(planetName);
            Rank rank = Rank.getValue(rankName);
            Jedi youngestJedi = JediController.getYoungestJediOfRankOnPlanet(rank, planet);
            System.out.println("The youngest jedi of rank " + rank.getDisplayName() + " on planet " + planet.getName() + " is: ");
            System.out.println(youngestJedi);
        }
        catch(PlanetDoesNotExistException | InvalidRankException | NoJediOfThisRankOnPlanetException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
