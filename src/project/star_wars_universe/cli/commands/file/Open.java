package project.star_wars_universe.cli.commands.file;

import project.star_wars_universe.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.FileAlreadyOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.data.DataLoadingException;
import project.star_wars_universe.exceptions.util.ParsingFailureException;
import project.star_wars_universe.resource.XMLFile;

import java.io.IOException;
import java.util.List;

/**
 * The {@link Command} responsible for opening a file and loading the app data.
 */
public class Open implements Command {
    /**
     * A reference to the {@link AppDataManager}.
     */
    AppDataManager appDataManager = AppDataManager.getInstance();

    /**
     * Loads the app data and notifies the user if the desired file was successfully opened.
     * If an exception occurs while the file is being read and processed ({@link ParsingFailureException}, {@link DataLoadingException},
     * {@link IOException}), an appropriate error message is displayed.
     * @param input the user input.
     * @throws FileAlreadyOpenedException if a file is already open.
     * @throws WrongArgumentsCountException if the arguments count is incorrect.
     */
    @Override
    public void execute(List<String> input) throws FileAlreadyOpenedException, WrongArgumentsCountException {
        if(input.size() != 2) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() != null) {
            throw new FileAlreadyOpenedException();
        }

        try {
            String path = input.get(1);
            appDataManager.loadAppData(new XMLFile(path));
            System.out.println("File " + path + " was successfully opened");
        }
        catch(ParsingFailureException | DataLoadingException | IOException ex) {
            if(ex instanceof ParsingFailureException) {
                String exceptionMessage = ((ParsingFailureException)ex).getException().getMessage();
                System.out.println("Data could not be read" + ((exceptionMessage != null) ? ": " + exceptionMessage : ""));
            }
            else if(ex instanceof DataLoadingException) {
                String exceptionMessage = ((DataLoadingException)ex).getException().getMessage();
                System.out.println("Data could not be read" + ((exceptionMessage != null) ? ": " + exceptionMessage : ""));
            }
            else if(ex instanceof IOException) {
                System.out.println(((IOException) ex).getMessage());
            }

            appDataManager.unloadAppData();
        }
    }
}
