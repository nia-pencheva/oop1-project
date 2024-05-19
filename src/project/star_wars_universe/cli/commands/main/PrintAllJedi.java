package project.star_wars_universe.cli.commands.main;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.data.repository.JediRepository;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.models.jedi.Jedi;

import java.util.List;

public class PrintAllJedi implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();
    /**
     * A reference to the {@link JediRepository}.
     */
    private JediRepository jediRepository = JediRepository.getInstance();

    /**
     * Prints all jedi (using {@link Jedi#toString()} implicitly) in the application
     * or notifies the user that there are no jedi yet.
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

        List<Jedi> allJedi = jediRepository.getJedi();
        if(allJedi.isEmpty()) {
            System.out.println("There are not jedi yet.");
        }
        else {
            for(Jedi jedi : allJedi) {
                System.out.println(jedi);
                System.out.println();
            }
        }
    }
}
