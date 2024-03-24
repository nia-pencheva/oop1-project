package project.star_wars_universe.app;

import project.star_wars_universe.app.cli.CLI;
import project.star_wars_universe.data.loading.general.XMLDataLoader;
import project.star_wars_universe.data.managers.DataManager;

public class App {
    public void start() throws Exception {
        DataManager.getInstance().useLoader(new XMLDataLoader());
        CLI.start();
    }
}
