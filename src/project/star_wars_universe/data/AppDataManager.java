package project.star_wars_universe.data;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.data.loading.JediDataLoader;
import project.star_wars_universe.data.loading.PlanetsDataLoader;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.entities.planets.Planet;
import project.star_wars_universe.repository.JediRepository;
import project.star_wars_universe.repository.PlanetsRepository;
import project.star_wars_universe.resource.File;

import java.util.Set;

public class AppDataManager {
    private static AppDataManager instance = null;
    private File openedFile = null;
    private DataLoader<Set<Jedi>> jediDataLoader = new JediDataLoader();
    private DataLoader<Set<Planet>> planetsDataLoader = new PlanetsDataLoader();

    private AppDataManager() {}

    public static AppDataManager getInstance() {
        if(instance == null) {
            instance = new AppDataManager();
        }

        return instance;
    }

    public void loadAppData(File file) throws Exception {
        if(this.openedFile != null) {
            throw new FileOpenedException;
        }

        this.openedFile = file;
        AppData appData = file.getParser().parse(openedFile.getData());
        jediDataLoader.load(appData.getJedi());
        planetsDataLoader.load(appData.getPlanets());
    }

    public void unloadAppData() {
        this.openedFile = null;
        jediDataLoader.unload();
        planetsDataLoader.unload();
    }

    public void saveAppData(File file) throws Exception {
        Set<Jedi> jedi = JediRepository.getInstance().getJedi();
        Set<Planet> planets = PlanetsRepository.getInstance().getPlanets();
        AppData appData = new AppData(jedi, planets);
        String data = file.getSerializer().serialize(appData);
        file.saveData(data);
    }

    public File getOpenedFile() {
        return openedFile;
    }
}
