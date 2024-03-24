package project.star_wars_universe.app.cli.commands.executability_checkers;

import project.star_wars_universe.contracts.cli.commands.ExecutablilityChecker;
import project.star_wars_universe.data.managers.DataManager;

public class NoFileOpenedChecker implements ExecutablilityChecker {
    @Override
    public boolean isExecutable() {
        return DataManager.getInstance().getResource() != null;
    }

    @Override
    public void printNotExecutableMessage() {
        System.out.println("No file opened (type \"open <file>\" to open a file)");
    }
}
