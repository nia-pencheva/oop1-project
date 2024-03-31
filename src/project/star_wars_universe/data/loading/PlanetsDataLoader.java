package project.star_wars_universe.data.loading;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.entities.planets.Planet;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.Set;

public class PlanetsDataLoader implements DataLoader<Set<Planet>> {
    private PlanetsRepository repository = PlanetsRepository.getInstance();

    @Override
    public void load(Set<Planet> planets) {
        for(Planet item : planets) {
            repository.add(item);
        }
    }

    @Override
    public void unload() {
        repository.removeAll();
    }
}
