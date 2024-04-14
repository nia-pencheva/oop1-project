package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.NoFileOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.resource.XMLFile;

import java.util.List;

public class SaveAs extends Command {
    List<String> input;

    public SaveAs(List<String> input) throws NoFileOpenedException, WrongArgumentsCountException {
        super(2);

        if(AppDataManager.getInstance().getOpenedFile() == null) {
            throw new NoFileOpenedException();
        }

        if(!hasCorrectArgumentsCount(input)) {
            throw new WrongArgumentsCountException();
        }

        this.input = input;
    }

    @Override
    public void execute() throws Exception {
        AppDataManager.getInstance().saveAppData(new XMLFile(input.get(1).replaceAll("\"", "")));
    }
}
