package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.util.SerializationFailureException;
import project.star_wars_universe.resource.file.XMLFile;

import java.io.IOException;
import java.util.List;

public class SaveAs extends Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    public SaveAs()  {
        super(2);
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException {
        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        try {
            String path = input.get(1);
            AppDataManager.getInstance().saveAppData(new XMLFile(path));
            System.out.println("Data was successfully saved to " + path);
        }
        catch(SerializationFailureException | IOException ex) {
            System.out.println("Writing to file was unsuccessful.");
        }
    }
}
