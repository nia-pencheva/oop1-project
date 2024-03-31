package project.star_wars_universe.data.loading.general;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.contracts.resource.Resource;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.data.loading.module.JediDataLoader;
import project.star_wars_universe.data.loading.module.PlanetsDataLoader;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.entities.planets.Planet;

import java.util.Map;
import java.util.Set;

public class GeneralDataLoader implements DataLoader<Resource> {
    private Parser<Map<String, Set>, String> parser;
    private DataLoader<Set<Jedi>> jediDataLoader = new JediDataLoader();
    private DataLoader<Set<Planet>> planetsDataLoader = new PlanetsDataLoader();

    public GeneralDataLoader(Parser<Map<String, Set>, String> parser) {
        this.parser = parser;
    }

    public void load(Resource resource) throws Exception {
        Map<String, Set> data = parser.parse(resource.getData());
        jediDataLoader.load(data.get("jediList"));
        planetsDataLoader.load(data.get("planetsList"));
    }

    public void unload() {
        jediDataLoader.unload();
        planetsDataLoader.unload();
    }
}
