package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.CommandExecutionException;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.jedi.InvalidRankException;
import project.star_wars_universe.exceptions.jedi_statistics.NoGrandMastersOnPlanetException;
import project.star_wars_universe.exceptions.jedi_statistics.NoJediOfThisRankOnPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.controllers.JediController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMostUsedSaberColor implements Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();
    private Map<Integer, Command> subCommands = new HashMap<>() {
        {
            put(2, new GetMostUsedSaberColorOfRankOnPlanet());
            put(3, new GetMostUsedSaberColorByGrandMasterOnPlanet());
        }
    };

    @Override
    public void execute(List<String> input) throws CommandExecutionException {
        Command subCommand = subCommands.get(input.size());

        if(subCommand == null) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        subCommand.execute(input);
    }

    private class GetMostUsedSaberColorByGrandMasterOnPlanet implements Command {
        @Override
        public void execute(List<String> input) throws CommandExecutionException {
            try {
                Planet planet = planetsRepository.getPlanetByName(input.get(1));
                System.out.println("The most used saber color which is used by at least one Grand Master jedi on the planet of " + planet.getName() + " is " + JediController.getMostUsedSaberColorByGrandMasterOnPlanet(planet).getColor());
            }
            catch(PlanetDoesNotExistException | NoGrandMastersOnPlanetException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private class GetMostUsedSaberColorOfRankOnPlanet implements Command {
        @Override
        public void execute(List<String> input) throws CommandExecutionException {
            try {
                Planet planet = planetsRepository.getPlanetByName(input.get(1));
                Rank rank = Rank.getValue(input.get(2));
                System.out.println("The most used saber color by jedi of rank " + rank.getDisplayName() + " on the planet of " + planet.getName() + " is " + JediController.getMostUsedSaberColorOfRankOnPlanet(rank, planet));
            }
            catch(PlanetDoesNotExistException | InvalidRankException | NoJediOfThisRankOnPlanetException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
