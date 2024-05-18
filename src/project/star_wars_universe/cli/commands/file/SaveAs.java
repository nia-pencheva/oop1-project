package project.star_wars_universe.cli.commands.file;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.util.SerializationFailureException;
import project.star_wars_universe.resource.file.XMLFile;

import java.io.IOException;
import java.util.List;

/**
 * The {@link Command} responsible for saving the app data to a specified file.
 */
public class SaveAs implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    private AppDataManager appDataManager = AppDataManager.getInstance();

    /**
     * Saves the app data to a specified file and notifies the user if the data was successfully saved.
     * If an exception occurs while the data is being processed and written, an appropriate error message is displayed.
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

        String path = input.get(1);

        try {
            appDataManager.saveAppData(new XMLFile(path));
            System.out.println("Data was successfully saved to " + path);
        }
        catch(SerializationFailureException | IOException ex) {
            System.out.println("Writing to file " + path + " was unsuccessful.");
        }
    }
}
