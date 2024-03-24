package project.star_wars_universe.app.cli.commands;

import project.star_wars_universe.app.cli.commands.executability_checkers.FileOpenedChecker;
import project.star_wars_universe.contracts.cli.commands.ExecutablilityChecker;
import project.star_wars_universe.data.managers.DataManager;
import project.star_wars_universe.resource.File;

import java.util.List;

public class Open extends Command {
    List<String> input;

    public Open(List<String> input) {
        super(2);
        this.input = input;
    }

    @Override
    public void execute() throws Exception {
        ExecutablilityChecker executablilityChecker = new FileOpenedChecker();

        if(executablilityChecker.isExecutable()) {
            if(enoughArguments(input)) {
                DataManager.getInstance().load(new File(input.get(1).replaceAll("\"", "")));
            }
        }
        else {
            executablilityChecker.printNotExecutableMessage();
        }
    }
}
