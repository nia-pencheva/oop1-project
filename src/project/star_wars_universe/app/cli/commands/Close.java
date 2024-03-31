package project.star_wars_universe.app.cli.commands;

import project.star_wars_universe.app.cli.commands.executability_checkers.NoFileOpenedChecker;
import project.star_wars_universe.contracts.cli.commands.ExecutablilityChecker;
import project.star_wars_universe.data.AppDataManager;

public class Close extends Command {
    public Close() {
        super(1);
    }

    @Override
    public void execute() throws Exception {
        ExecutablilityChecker executablilityChecker = new NoFileOpenedChecker();

        if(executablilityChecker.isExecutable()) {
            AppDataManager.getInstance().unloadAppData();
        }
        else {
            executablilityChecker.printNotExecutableMessage();
        }
    }
}
