package project.star_wars_universe.app;

import project.star_wars_universe.app.cli.CLI;
import project.star_wars_universe.data.loading.general.GeneralDataLoader;
import project.star_wars_universe.data.managers.DataManager;
import project.star_wars_universe.util.parsers.xml.XMLDataParser;

public class App {
    public void start() throws Exception {
        DataManager.getInstance().useLoader(new GeneralDataLoader(new XMLDataParser()));
        CLI.start();
    }
}
