package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;

public class Close extends Command {
    public Close() throws NoFileOpenedException {
        super(1);

        if(AppDataManager.getInstance().getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }
    }

    @Override
    public void execute() {
        AppDataManager.getInstance().unloadAppData();
    }
}
