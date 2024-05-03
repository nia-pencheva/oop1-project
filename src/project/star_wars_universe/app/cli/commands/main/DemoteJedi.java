package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.CommandExecutionException;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.jedi.JediDoesNotExistException;
import project.star_wars_universe.exceptions.jedi.LowestRankReachedException;
import project.star_wars_universe.exceptions.jedi.InvalidPromotionMultiplierException;
import project.star_wars_universe.exceptions.jedi.InvalidPowerException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.repository.JediRepository;

import java.util.List;

public class DemoteJedi implements Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();
    private JediRepository jediRepository = JediRepository.getInstance();

    @Override
    public void execute(List<String> input) throws CommandExecutionException {
        if(input.size() != 3) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        String name = input.get(1);
        double multiplier = Double.parseDouble(input.get(2));

        try {
            Jedi jedi = jediRepository.getJediByName(name);
            jedi.demoteJedi(multiplier);
            System.out.println("Jedi " + name + " has been successfully demoted to rank " + jedi.getRank().getDisplayName());
        }
        catch(JediDoesNotExistException | LowestRankReachedException | InvalidPromotionMultiplierException ex) {
            System.out.println(ex.getMessage());
        }
        catch(InvalidPowerException ex) {
            System.out.println("The jedi's power cannot be changed by multiplier " + multiplier + " because it causes the power to exceed the limits (0 - 1000)");
        }
    }
}
