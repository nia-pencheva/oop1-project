package project.star_wars_universe.app.cli.commands.file;

import project.star_wars_universe.app.cli.commands.Command;
import project.star_wars_universe.exceptions.cli.FileAlreadyOpenedException;
import project.star_wars_universe.exceptions.cli.WrongArgumentsCountException;
import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.exceptions.data.DataLoadingException;
import project.star_wars_universe.exceptions.util.ParsingFailureException;
import project.star_wars_universe.resource.file.XMLFile;

import java.io.IOException;
import java.util.List;

public class Open extends Command {
    private List<String> input;

    public Open(List<String> input) throws FileAlreadyOpenedException, WrongArgumentsCountException {
        super(2);

        if(AppDataManager.getInstance().getOpenedFile() != null) {
            throw new FileAlreadyOpenedException();
        }

        if(!hasCorrectArgumentsCount(input)) {
            throw new WrongArgumentsCountException();
        }

        this.input = input;
    }

    @Override
    public void execute() {
        try {
            String path = input.get(1);
            AppDataManager.getInstance().loadAppData(new XMLFile(path));
            System.out.println("File " + path + " was successfully opened");
        }
        catch(ParsingFailureException | DataLoadingException | IOException ex) {
            if(ex instanceof ParsingFailureException) {
                String exceptionMessage = ((ParsingFailureException)ex).getException().getMessage();
                System.out.println("Data could not be read" + ((exceptionMessage != null) ? ": " + exceptionMessage : ""));
            }
            else if(ex instanceof DataLoadingException) {
                String exceptionMessage = ((DataLoadingException)ex).getException().getMessage();
                System.out.println("Data could not be read" + ((exceptionMessage != null) ? ": " + exceptionMessage : ""));
            }
            else if(ex instanceof IOException) {
                System.out.println("File could not be read.");
            }

            AppDataManager.getInstance().unloadAppData();
        }
    }
}
