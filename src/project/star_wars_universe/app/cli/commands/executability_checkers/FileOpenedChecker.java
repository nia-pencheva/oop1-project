package project.star_wars_universe.app.cli.commands.executability_checkers;

import project.star_wars_universe.contracts.cli.commands.ExecutablilityChecker;
import project.star_wars_universe.data.managers.DataManager;

public class FileOpenedChecker implements ExecutablilityChecker {

    @Override
    public boolean isExecutable() {
        return DataManager.getInstance().getResource() == null;
    }

    @Override
    public void printNotExecutableMessage() {
        System.out.println("A file is already open (type \"close\" to close it)");
    }
}
