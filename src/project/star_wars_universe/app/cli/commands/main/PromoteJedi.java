package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;

import java.util.List;

public class PromoteJedi extends Command {
    private AppDataManager appDataManager = AppDataManager.getInstance();

    public PromoteJedi() {
        super(3);
    }

    @Override
    public void execute(List<String> input) throws NoFileOpenedException {
        if(AppDataManager.getInstance().getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        String name = input.get(1);
        String planetName = input.get(2);
    }
}
