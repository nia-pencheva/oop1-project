package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;

import java.util.List;

public class GetYoungestJedi extends Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    public GetYoungestJedi()  {
        super(2);
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException {
        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }
    }
}
