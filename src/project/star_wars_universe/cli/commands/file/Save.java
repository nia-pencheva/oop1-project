package project.star_wars_universe.cli.commands.file;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.util.SerializationFailureException;
import project.star_wars_universe.resource.file.File;

import java.io.IOException;
import java.util.List;

/**
 * The {@link Command} for saving the app data to the currently opened file.
 */
public class Save implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();

    /**
     * Saves the app data to the currently opened file and notifies the user if the data was successfully saved.
     * If an exception occurs while the data is being processed and written ({@link SerializationFailureException},
     * {@link IOException}), an appropriate error message is displayed.
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

        File openedFile = appDataManager.getOpenedFile();

        try {
            appDataManager.saveAppData(openedFile);
            System.out.println("Data was successfully saved to " + openedFile.getPath());
        }
        catch(SerializationFailureException | IOException ex) {
            System.out.println("Saving data to file " + openedFile.getPath() + " was unsuccessful");
        }
    }
}
