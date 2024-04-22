package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.util.SerializationFailureException;
import project.star_wars_universe.resource.File;
import project.star_wars_universe.resource.XMLFile;

import java.io.IOException;
import java.util.List;

public class SaveAs extends Command {
    private List<String> input;

    public SaveAs(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        super(2);

        if(AppDataManager.getInstance().getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        if(!hasCorrectArgumentsCount(input)) {
            throw new WrongArgumentsCountException();
        }

        this.input = input;
    }

    @Override
    public void execute() {
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
