package project.star_wars_universe.cli.commands.main;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.jedi.*;
import project.star_wars_universe.exceptions.util.ParsingFailureException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.jedi.enums.SaberColor;
import project.star_wars_universe.data.repository.JediRepository;
import project.star_wars_universe.data.repository.PlanetsRepository;
import project.star_wars_universe.util.parsers.base_type.DoubleParser;
import project.star_wars_universe.util.parsers.base_type.IntegerParser;

import java.util.List;

/**
 * The {@link Command} for creating a new jedi.
 */
public class CreateJedi implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();
    /**
     * A reference to the {@link PlanetsRepository}.
     */
    private PlanetsRepository planetsRepository = PlanetsRepository.getInstance();
    /**
     * A reference to the {@link JediRepository}.
     */
    private JediRepository jediRepository = JediRepository.getInstance();

    /**
     * Parses the user input, creates a new {@link Jedi} object, adds it to the specified planet's {@link project.star_wars_universe.models.planets.Planet#jediPopulation}
     * and to the {@link JediRepository} and notifies the user if the operation was successfully completed. If any of the input data is invalid ({@link InvalidNameException}, {@link InvalidRankException},
     * {@link InvalidAgeException}, {@link InvalidSaberColorException}, {@link InvalidPowerException}), a {@link ParsingFailureException} occurs, the planet does not exist
     * ({@link PlanetDoesNotExistException}), a jedi with that name already exists on this planet ({@link JediExistsOnThisPlanetException}) or the jedi already exists in the
     * {@link JediRepository} ({@link JediAlreadyExistsException}), an appropriate error message is displayed.
     * @param input the user input.
     * @throws NoFileOpenedException if a file is not opened.
     * @throws WrongArgumentsCountException if the arguments count is incorrect.
     */
    @Override
    public void execute(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        if(input.size() != 7) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            String planetName = input.get(1);
            String name = input.get(2);
            Rank rank = Rank.getValue(input.get(3));
            int age = IntegerParser.parse(input.get(4));
            SaberColor saberColor = SaberColor.getValue(input.get(5));
            double power = DoubleParser.parse(input.get(6));
            Jedi newJedi = new Jedi(name, rank, age, saberColor, power);


            if(!jediRepository.jediExists(newJedi)) {
                planetsRepository.getPlanetByName(planetName).addJedi(newJedi);
                jediRepository.add(newJedi);
            }
            else {
                throw new JediAlreadyExistsException();
            }

            System.out.println("Jedi " + name + " was successfully created!");
        }
        catch(InvalidNameException | JediAlreadyExistsException | InvalidRankException | InvalidAgeException | InvalidSaberColorException | InvalidPowerException | PlanetDoesNotExistException | JediExistsOnThisPlanetException ex) {
            System.out.println(ex.getMessage());
        }
        catch (ParsingFailureException ex) {
            System.out.println(ex.getException().getMessage());
        }
    }
}
