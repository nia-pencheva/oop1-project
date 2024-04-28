package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.data.AppDataManager;

import java.util.List;

public class Close extends Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    public Close() {
        super(1);
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException {
        if(appDataManager.getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        AppDataManager.getInstance().unloadAppData();
    }
}
