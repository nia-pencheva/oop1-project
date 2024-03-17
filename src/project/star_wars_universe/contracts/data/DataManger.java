package project.star_wars_universe.contracts.data;

import project.star_wars_universe.contracts.resource.Resource;

public interface DataManger {
    public void load(Resource resource) throws Exception;
    public void unload();
}
