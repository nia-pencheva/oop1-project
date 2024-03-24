package project.star_wars_universe.data.loading.general;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.contracts.resource.Resource;
import project.star_wars_universe.data.loading.module.JediDataLoader;
import project.star_wars_universe.data.loading.module.PlanetsDataLoader;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.entities.planets.Planet;

import java.util.Set;

public abstract class GeneralDataLoader implements DataLoader<Resource> {
    private DataLoader<Set<Jedi>> jediDataLoader = new JediDataLoader();
    private DataLoader<Set<Planet>> planetsDataLoader = new PlanetsDataLoader();

    public abstract void load(Resource resource) throws Exception;

    public void unload() {
        jediDataLoader.unload();
        planetsDataLoader.unload();
    }

    protected DataLoader<Set<Jedi>> getJediDataLoader() {
        return this.jediDataLoader;
    }

    protected DataLoader<Set<Planet>> getPlanetsDataLoader() { return this.planetsDataLoader; }
}
