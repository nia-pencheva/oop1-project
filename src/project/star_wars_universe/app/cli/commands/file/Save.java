package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.util.SerializationFailureException;
import project.star_wars_universe.resource.File;

import java.io.IOException;

public class Save extends Command {

    public Save() throws NoFileOpenedException {
        super(1);

        if(AppDataManager.getInstance().getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }
    }

    @Override
    public void execute() {
        try {
            File openedFile = AppDataManager.getInstance().getOpenedFile();
            AppDataManager.getInstance().saveAppData(openedFile);
            System.out.println("Data was successfully saved to " + openedFile.getPath());
        }
        catch(SerializationFailureException | IOException ex) {
            System.out.println("Writing to file was unsuccessful.");
        }
    }
}
