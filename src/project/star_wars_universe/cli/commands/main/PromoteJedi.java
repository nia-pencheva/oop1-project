package project.star_wars_universe.cli.commands.main;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.jedi.*;
import project.star_wars_universe.exceptions.util.ParsingFailureException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.data.repository.JediRepository;
import project.star_wars_universe.util.parsers.base_type.DoubleParser;

import java.util.List;

/**
 * The {@link Command} for promoting a jedi.
 */
public class PromoteJedi implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();
    /**
     * A reference to the {@link JediRepository}.
     */
    private JediRepository jediRepository = JediRepository.getInstance();

    /**
     * Gets the specified jedi from the {@link JediRepository}, promotes them and displays a message if the operation was successful.
     * If a {@link ParsingFailureException} occurs, the specified jedi does not exist in the {@link JediRepository}
     * ({@link JediDoesNotExistException}), the highest possible rank is reached ({@link HighestRankReachedException}),
     * the promotion multiplier is invalid ({@link InvalidPromotionMultiplierException}) or
     * that jedi's power cannot be changed by the specified multiplier because it causes
     * the power to exceed the allowed limits ({@link InvalidPowerException}),
     * an appropriate error message is displayed.
     * @param input the user input.
     * @throws NoFileOpenedException if a file is not opened.
     * @throws WrongArgumentsCountException if the arguments count is incorrect.
     */
    @Override
    public void execute(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        if(input.size() != 3) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        String name = input.get(1);

        try {
            double multiplier = DoubleParser.parse(input.get(2));
            Jedi jedi = jediRepository.getJediByName(name);

            jedi.promoteJedi(multiplier);
            System.out.println("Jedi " + name + " has been successfully promoted to rank " + jedi.getRank().getDisplayName());
        }
        catch(JediDoesNotExistException | HighestRankReachedException | InvalidPromotionMultiplierException ex) {
            System.out.println(ex.getMessage());
        }
        catch(InvalidPowerException ex) {
            System.out.println("The jedi's power cannot be changed by this multiplier because it causes the power to exceed the limits (0 - 1000)");
        }
        catch (ParsingFailureException ex) {
            System.out.println(ex.getException().getMessage());
        }
    }
}
