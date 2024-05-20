package project.star_wars_universe.cli.commands.main;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.data.repository.PlanetsRepository;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.models.planets.Planet;

import java.util.List;

/**
 * The {@link Command} for printing all planets. This command is not a part of the
 * project's requirements, but I decided to add it because I felt that a user
 * might want to see a list of all the planets in the application.
 */
public class PrintAllPlanets implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();
    /**
     * A reference to the {@link PlanetsRepository}.
     */
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();

    /**
     * Prints all planets (using {@link Planet#toString()} implicitly) in the application
     * or notifies the user that there are no planets yet.
     * @param input the user input.
     * @throws NoFileOpenedException if a file is not opened.
     * @throws WrongArgumentsCountException if the arguments count is incorrect.
     */
    @Override
    public void execute(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        if(input.size() != 1) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        List<Planet> allPlanets = planetsRepository.getPlanets();
        if(allPlanets.isEmpty()) {
            System.out.println("There are not planets yet.");
        }
        else {
            for(Planet planet : allPlanets) {
                System.out.println(planet);
                System.out.println();
            }
        }
    }
}
