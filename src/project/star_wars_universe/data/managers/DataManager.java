package project.star_wars_universe.data.managers;

import project.star_wars_universe.contracts.data.DataStoreManager;
import project.star_wars_universe.contracts.resource.Resource;
import project.star_wars_universe.data.loading.general.GeneralDataLoader;

public class DataManager {
    private static DataManager instance = null;
    private GeneralDataLoader dataLoader;
    private DataStoreManager storeManager;
    private Resource resource = null;

    private DataManager() {}

    public static DataManager getInstance() {
        if(instance == null) {
            instance = new DataManager();
        }

        return instance;
    }

    public void useLoader(GeneralDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void useStoreManager(DataStoreManager storeManager) {
        this.storeManager = storeManager;
    }

    public void load(Resource resource) throws Exception {
        this.resource = resource;
        dataLoader.load(resource);
    }

    public void unload() {
        this.resource = null;
        dataLoader.unload();
    }

    public void save() {
        storeManager.store();
    }

    public Resource getResource() {
        return resource;
    }
}
