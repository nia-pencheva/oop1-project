package project.star_wars_universe.data;

import project.star_wars_universe.contracts.resource.Resource;

public class DataManager {
    private static DataManager instance = null;
    private Resource resource = null;

    private DataManager() {}

    public static DataManager getInstance() {
        if(instance == null) {
            instance = new DataManager();
        }

        return instance;
    }

    public void load(Resource resource) throws Exception {
        this.resource = resource;
        String data = this.resource.getData();
    }

    public void unload() {
        this.resource = null;
    }
}
