package project.star_wars_universe.cli.commands.main;

import project.star_wars_universe.cli.commands.Command;
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
import project.star_wars_universe.data.repository.PlanetsRepository;
import project.star_wars_universe.services.JediStatisticsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@link Command} for getting the most used saber color by a certain set of criteria.
 */
public class GetMostUsedSaberColor implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();
    /**
     * A reference to the {@link PlanetsRepository}.
     */
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();
    /**
     * A {@code Map} containing the number of arguments the command takes
     * as key and the corresponding {@link Command} handler as value.
     */
    private Map<Integer, Command> subCommands = new HashMap<>() {
        {
            put(2, new GetMostUsedSaberColorByGrandMasterOnPlanet());
            put(3, new GetMostUsedSaberColorOfRankOnPlanet());
        }
    };

    /**
     * Extracts a command handler corresponding to the number of arguments passed
     * from the {@link GetMostUsedSaberColor#subCommands} map. If it fails to find such a command, a {@link WrongArgumentsCountException}
     * is thrown. If there is no opened file, a {@link NoFileOpenedException} is thrown.
     * If a relevant command is found, it gets executed (potentially throwing the general {@link CommandExecutionException}).
     * @param input the user input.
     * @throws CommandExecutionException if command execution fails.
     */
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

    /**
     * A nested class implementing the variation of the GetMostUsedSaberColor command
     * which takes 2 arguments and finds and displays the most used saber color
     * by at least one Grand Master on a specified planet.
     */
    private class GetMostUsedSaberColorByGrandMasterOnPlanet implements Command {
        /**
         * Finds and displays the most used saber color by at least one Grand Master
         * on a specified planet. If the specified planet
         * does not exist ({@link PlanetDoesNotExistException}) or there are no Grand Masters
         * on the specified planet ({@link NoGrandMastersOnPlanetException}), an appropriate
         * error message is displayed.
         * @param input the user input.
         * @throws CommandExecutionException if command execution fails.
         */
        @Override
        public void execute(List<String> input) throws CommandExecutionException {
            try {
                Planet planet = planetsRepository.getPlanetByName(input.get(1));
                System.out.println("The most used saber color which is used by at least one Grand Master jedi on the planet of " + planet.getName() + " is " + JediStatisticsService.getMostUsedSaberColorByGrandMasterOnPlanet(planet).getColor());
            }
            catch(PlanetDoesNotExistException | NoGrandMastersOnPlanetException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * A nested class implementing the variation of the GetMostUsedSaberColor command
     * which takes 3 arguments and finds and displays the most used saber color
     * by the jedi of a specified rank on a given planet.
     */
    private class GetMostUsedSaberColorOfRankOnPlanet implements Command {
        /**
         * Finds and displays the most used saber color by the jedi of a specified rank on a given planet.
         * If the specified planet does not exist ({@link PlanetDoesNotExistException}),
         * the value supplied for rank is invalid ({@link InvalidRankException}) or there are no jedi
         * of the specified rank on the given planet ({@link NoJediOfThisRankOnPlanetException}),
         * an appropriate error message is displayed.
         * @param input the user input.
         * @throws CommandExecutionException if command execution fails.
         */
        @Override
        public void execute(List<String> input) throws CommandExecutionException {
            try {
                Planet planet = planetsRepository.getPlanetByName(input.get(1));
                Rank rank = Rank.getValue(input.get(2));
                System.out.println("The most used saber color by jedi of rank " + rank.getDisplayName() + " on the planet of " + planet.getName() + " is " + JediStatisticsService.getMostUsedSaberColorOfRankOnPlanet(rank, planet).getColor());
            }
            catch(PlanetDoesNotExistException | InvalidRankException | NoJediOfThisRankOnPlanetException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
