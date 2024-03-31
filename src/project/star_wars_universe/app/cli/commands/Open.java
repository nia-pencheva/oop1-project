package project.star_wars_universe.app.cli.commands;

import project.star_wars_universe.app.cli.commands.executability_checkers.FileOpenedChecker;
import project.star_wars_universe.contracts.cli.commands.ExecutablilityChecker;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.resource.File;
import project.star_wars_universe.resource.XMLFile;
import project.star_wars_universe.util.parsers.xml.XMLParser;

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
                AppDataManager.getInstance().loadAppData(new XMLFile(input.get(1).replaceAll("\"", "")));
            }
        }
        else {
            executablilityChecker.printNotExecutableMessage();
        }
    }
}
