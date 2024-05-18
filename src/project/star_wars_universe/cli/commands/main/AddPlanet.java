package project.star_wars_universe.cli.commands.main;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.planets.InvalidNameException;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.planets.PlanetAlreadyExistsException;
import project.star_wars_universe.data.repository.PlanetsRepository;

import java.util.List;

/**
 * The {@link Command} for adding a planet to the application.
 */
public class AddPlanet implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();
    /**
     * A reference to the {@link PlanetsRepository}.
     */
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();

    /**
     * Adds a new {@link Planet} to the {@link PlanetsRepository} and notifies the user if the operation was successful.
     * If the planet already exists ({@link PlanetAlreadyExistsException}) or the planet name is invalid ({@link InvalidNameException}),
     * an appropriate error message is displayed.
     * @param input the user input.
     * @throws NoFileOpenedException if a file is not opened.
     * @throws WrongArgumentsCountException if the arguments count is incorrect.
     */
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
        catch(PlanetAlreadyExistsException | InvalidNameException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
