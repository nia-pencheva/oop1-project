package project.star_wars_universe.app.cli.commands;

import project.star_wars_universe.app.cli.commands.executability_checkers.NoFileOpenedChecker;
import project.star_wars_universe.contracts.cli.commands.ExecutablilityChecker;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.resource.File;

public class Save extends Command {

    public Save() {
        super(1);
    }

    @Override
    public void execute() throws Exception {
        ExecutablilityChecker executablilityChecker = new NoFileOpenedChecker();

        if(executablilityChecker.isExecutable()) {
            File openedFile = AppDataManager.getInstance().getOpenedFile();
            AppDataManager.getInstance().saveAppData(openedFile);
        }
        else {
            executablilityChecker.printNotExecutableMessage();
        }
    }
}
