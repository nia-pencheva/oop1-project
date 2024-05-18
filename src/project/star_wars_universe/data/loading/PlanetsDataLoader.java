package project.star_wars_universe.data.loading;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.data.DataLoadingException;
import project.star_wars_universe.exceptions.planets.PlanetAlreadyExistsException;
import project.star_wars_universe.data.repository.PlanetsRepository;

import java.util.List;

public class PlanetsDataLoader implements DataLoader<List<Planet>> {
    private PlanetsRepository repository = PlanetsRepository.getInstance();

    @Override
    public void load(List<Planet> planets) throws DataLoadingException {
        try {
            for(Planet item : planets) {
                repository.add(item);
            }
        }
        catch(PlanetAlreadyExistsException ex) {
            throw new DataLoadingException(ex);
        }
    }

    @Override
    public void unload() {
        repository.removeAll();
    }
}
