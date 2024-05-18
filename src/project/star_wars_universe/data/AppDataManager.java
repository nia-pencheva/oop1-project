package project.star_wars_universe.data;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.data.loading.JediDataLoader;
import project.star_wars_universe.data.loading.PlanetsDataLoader;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.data.DataLoadingException;
import project.star_wars_universe.exceptions.util.ParsingFailureException;
import project.star_wars_universe.exceptions.util.SerializationFailureException;
import project.star_wars_universe.data.repository.JediRepository;
import project.star_wars_universe.data.repository.PlanetsRepository;
import project.star_wars_universe.resource.File;

import java.io.IOException;
import java.util.List;

public class AppDataManager {
    private static AppDataManager instance = null;
    private File openedFile = null;
    private DataLoader<List<Jedi>> jediDataLoader = new JediDataLoader();
    private DataLoader<List<Planet>> planetsDataLoader = new PlanetsDataLoader();

    private AppDataManager() {}

    public static AppDataManager getInstance() {
        if(instance == null) {
            instance = new AppDataManager();
        }

        return instance;
    }

    public void loadAppData(File file) throws ParsingFailureException, DataLoadingException, IOException {
        this.openedFile = file;

        String content = openedFile.getData();
        if(!content.isEmpty()) {
            AppData appData = file.getParser().parse(openedFile.getData());
            jediDataLoader.load(appData.getJedi());
            planetsDataLoader.load(appData.getPlanets());
        }
    }

    public void unloadAppData() {
        this.openedFile = null;
        jediDataLoader.unload();
        planetsDataLoader.unload();
    }

    public void saveAppData(File file) throws SerializationFailureException, IOException {
        List<Jedi> jedi = JediRepository.getInstance().getJedi();
        List<Planet> planets = PlanetsRepository.getInstance().getPlanets();
        AppData appData = new AppData(jedi, planets);
        String data = file.getSerializer().serialize(appData);
        file.saveData(data);
    }

    public File getOpenedFile() {
        return openedFile;
    }
}
