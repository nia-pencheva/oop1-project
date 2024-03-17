package project.star_wars_universe.data.loaders;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.entities.planets.Planet;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.Set;

public class PlanetsDataLoader implements DataLoader<Set<Planet>> {
    @Override
    public void load(Set<Planet> planets) {
        PlanetsRepository repository = PlanetsRepository.getInstance();

        for(Planet item : planets) {
            repository.add(item);
        }
    }

    @Override
    public void unload() {

    }
}
