package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.exceptions.util.SerializationFailureException;
import project.star_wars_universe.resource.file.File;

import java.io.IOException;
import java.util.List;

public class Save implements Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

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
