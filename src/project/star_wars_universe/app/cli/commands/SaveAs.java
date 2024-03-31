package project.star_wars_universe.app.cli.commands;

import project.star_wars_universe.data.AppDataManager;
import project.star_wars_universe.resource.XMLFile;

import java.util.List;

public class SaveAs extends Command {
    List<String> input;

    public SaveAs(List<String> input) {
        super(2);
        this.input = input;
    }

    @Override
    public void execute() throws Exception {
        AppDataManager.getInstance().saveAppData(new XMLFile(input.get(1).replaceAll("\"", "")));
    }
}
