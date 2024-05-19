package project.star_wars_universe.data.loading;

import project.star_wars_universe.contracts.data.DataLoader;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.data.DataLoadingException;
import project.star_wars_universe.exceptions.planets.PlanetAlreadyExistsException;
import project.star_wars_universe.data.repository.PlanetsRepository;

import java.util.List;

/**
 * Loads and unloads {@link Planet} entities into the application. This class
 * serves as an intermediate layer between the more general
 * {@link project.star_wars_universe.data.AppDataManager} class and the
 * {@link PlanetsRepository} in order to decrease coupling and encapsulate
 * the logic for loading and unloading the {@link Planet} entities specifically.
 */
public class PlanetsDataLoader implements DataLoader<List<Planet>> {
    /**
     * A reference to the {@link PlanetsRepository}.
     */
    private PlanetsRepository repository = PlanetsRepository.getInstance();

    /**
     * Loads {@link Planet}s into the application. It iterates through the
     * provided {@code List} of {@link Planet}s and adds them to the
     * {@link PlanetsRepository} using its {@link PlanetsRepository#add(Planet)} method.
     * If a {@link Planet} already exists in the repository,
     * a {@link PlanetAlreadyExistsException} exception is thrown from
     * the {@link PlanetsRepository#add(Planet)} method. This exception is then
     * passed to a {@link DataLoadingException} instance, from which the specific exception
     * can later be extracted using the {@link DataLoadingException#getException()} method.
     * @param planets the {@code List} of {@link Planet}s that should be loaded in the {@link PlanetsRepository}.
     * @throws DataLoadingException if an exception occurs during the loading of the planets.
     */
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

    /**
     * Unloads the {@link Planet} entities from the application. It invokes the {@link PlanetsRepository#removeAll()}
     * method which empties the repository.
     */
    @Override
    public void unload() {
        repository.removeAll();
    }
}
