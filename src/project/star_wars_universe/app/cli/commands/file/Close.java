package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.contracts.cli.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;

import java.util.List;

public class Close implements Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    @Override
    public void execute(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        if(input.size() != 1) {
            throw new WrongArgumentsCountException();
        }

        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        appDataManager.unloadAppData();

        System.out.println("File was successfully closed.");
    }
}
