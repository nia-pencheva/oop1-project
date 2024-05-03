package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.jedi.InvalidRankException;
import project.star_wars_universe.exceptions.jedi_statistics.NoGrandMastersOnPlanetException;
import project.star_wars_universe.exceptions.jedi_statistics.NoJediOfThisRankOnPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.services.JediStatisticsService;

import java.util.List;

public class GetMostUsedSaberColor implements Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();

    @Override
    public void execute(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        if(input.size() < 2 || input.size() > 3) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            if(input.size() == 2) {
                Planet planet = planetsRepository.getPlanetByName(input.get(1));
                System.out.println("The most used saber color which is used by at least one Grand Master jedi on the planet of " + planet.getName() + " is " + JediStatisticsService.getMostUsedSaberColorByGrandMasterOnPlanet(planet).getColor());
            }

            if(input.size() == 3) {
                Planet planet = planetsRepository.getPlanetByName(input.get(1));
                Rank rank = Rank.getValue(input.get(2));
                System.out.println("The most used saber color by jedi of rank " + rank.getDisplayName() + " on the planet of " + planet.getName() + " is " + JediStatisticsService.getMostUsedSaberColorOfRankOnPlanet(rank, planet));
            }
        }
        catch (PlanetDoesNotExistException | NoGrandMastersOnPlanetException | InvalidRankException | NoJediOfThisRankOnPlanetException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
