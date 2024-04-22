package project.star_wars_universe.app.cli.commands.main;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;

import java.util.List;

public class PromoteJedi extends Command {
    private List<String> input;

    public PromoteJedi(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        super(3);

        if(AppDataManager.getInstance().getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        if(!hasCorrectArgumentsCount(input)) {
            throw new WrongArgumentsCountException();
        }

        this.input = input;
    }

    @Override
    public void execute() {
        String name = input.get(1);
        String planetName = input.get(2);
    }
}
