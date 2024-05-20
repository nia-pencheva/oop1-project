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

/**
 * A singleton class that manages the application data and tracks the opened file.
 * This class is implemented using the singleton pattern, because there should only be one instance
 * of the class that manages the application data - otherwise unexpected behaviours may occur (such as
 * data loss).
 */
public class AppDataManager {
    /**
     * The instance of the {@link AppDataManager}.
     */
    private static AppDataManager instance = null;
    /**
     * A reference to the currently opened file.
     */
    private File openedFile = null;
    /**
     * A {@link JediDataLoader} responsible for loading and unloading data about the jedi of the application.
     */
    private DataLoader<List<Jedi>> jediDataLoader = new JediDataLoader();
    /**
     * A {@link PlanetsDataLoader} responsible for loading and unloading data about the planets of the application.
     */
    private DataLoader<List<Planet>> planetsDataLoader = new PlanetsDataLoader();

    /**
     * Initializes the class. This constructor is private in accordance
     * to the rules for implementing the singleton pattern.
     */
    private AppDataManager() {}

    /**
     * Gets the {@link AppDataManager} instance. Here, the lazy loading approach is
     * used - the singleton instance is only created when it is first accessed.
     * @return the {@link AppDataManager} instance.
     */
    public static AppDataManager getInstance() {
        if(instance == null) {
            instance = new AppDataManager();
        }

        return instance;
    }

    /**
     * Loads data into the application. First the {@link File} from which data should be
     * loaded is assigned to the {@link AppDataManager#openedFile} field.
     * Secondly, the data from the file is retrieved. If it is not empty,
     * the data is first parsed using the {@link project.star_wars_universe.contracts.util.Parser}
     * of the specified {@link File} into an {@link AppData} object.
     * Then, the application data is loaded using the {@link AppDataManager#jediDataLoader} for the
     * jedi data and the {@link AppDataManager#planetsDataLoader} for the planets data respectively.
     * @param file the file from which data should be loaded.
     * @throws ParsingFailureException if an exception occurs during the parsing the file data.
     * @throws DataLoadingException if an exception occurs during the loading of the data into the application.
     * @throws IOException if an exception occurs while the file is being read.
     */
    public void loadAppData(File file) throws ParsingFailureException, DataLoadingException, IOException {
        this.openedFile = file;

        String content = openedFile.getData();
        if(!content.isEmpty()) {
            AppData appData = file.getParser().parse(openedFile.getData());
            jediDataLoader.load(appData.getJedi());
            planetsDataLoader.load(appData.getPlanets());
        }
    }

    /**
     * Unloads the application data. The {@link AppDataManager#openedFile} is set to null
     * to signify that there is now no opened file. The jedi data is unloaded using the {@link AppDataManager#jediDataLoader}
     * and the planets data is unloaded using the {@link AppDataManager#planetsDataLoader}.
     */
    public void unloadAppData() {
        this.openedFile = null;
        jediDataLoader.unload();
        planetsDataLoader.unload();
    }

    /**
     * Saves the application data to the specified file. First the {@code List}s of
     * {@link Jedi} and {@link Planet}s are retrieved from the {@link JediRepository} and the
     * {@link PlanetsRepository} respectively. Then they are used to create an {@link AppData}
     * object. The object is serialized using the {@link project.star_wars_universe.contracts.util.Serializer}
     * of the specified {@link File} and afterwards it is written to it.
     * @param file the file to which the application data should be written.
     * @throws SerializationFailureException if an exception occurs during the serialization of the applicaton data.
     * @throws IOException if an exception occurs while the file is being written to.
     */
    public void saveAppData(File file) throws SerializationFailureException, IOException {
        List<Jedi> jedi = JediRepository.getInstance().getJedi();
        List<Planet> planets = PlanetsRepository.getInstance().getPlanets();
        AppData appData = new AppData(jedi, planets);
        String data = file.getSerializer().serialize(appData);
        file.saveData(data);
    }

    /**
     * Gets the currently opened file.
     * @return the currently opened file or null if no file is opened at the moment.
     */
    public File getOpenedFile() {
        return openedFile;
    }
}
