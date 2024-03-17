package project.star_wars_universe.data.managers;

import org.w3c.dom.Document;
import project.star_wars_universe.contracts.data.DataManger;
import project.star_wars_universe.contracts.resource.Resource;
import project.star_wars_universe.data.loaders.JediDataLoader;
import project.star_wars_universe.data.loaders.PlanetsDataLoader;
import project.star_wars_universe.parser.xml.XMLDataParser;

import java.util.Map;
import java.util.Set;

public class XMLDataManager implements DataManger {
    private static XMLDataManager instance = null;
    private XMLDataParser parser = new XMLDataParser();
    private JediDataLoader jediDataLoader = new JediDataLoader();
    private PlanetsDataLoader planetsDataLoader = new PlanetsDataLoader();
    private Resource<String> resource = null;

    private XMLDataManager() {}

    public static XMLDataManager getInstance() {
        if(instance == null) {
            instance = new XMLDataManager();
        }

        return instance;
    }

    public void load(Resource resource) throws Exception {
        this.resource = resource;
        Map<String, Set> data = parser.parse(this.resource.getData());
        jediDataLoader.load(data.get("jediList"));
        planetsDataLoader.load(data.get("planetsList"));
    }

    public void unload() {
        this.resource = null;
    }
}
