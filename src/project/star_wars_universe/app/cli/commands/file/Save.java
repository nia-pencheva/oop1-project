package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.util.SerializationFailureException;
import project.star_wars_universe.resource.file.File;

import java.io.IOException;
import java.util.List;

public class Save extends Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    public Save() {
        super(1);
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException {
        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

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
