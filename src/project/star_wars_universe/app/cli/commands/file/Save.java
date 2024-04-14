package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.app.cli.exceptions.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;

public class Save extends Command {

    public Save() throws NoFileOpenedException {
        super(1);

        if(AppDataManager.getInstance().getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }
    }

    @Override
    public void execute() throws Exception {
        AppDataManager.getInstance().saveAppData(AppDataManager.getInstance().getOpenedFile());
    }
}
