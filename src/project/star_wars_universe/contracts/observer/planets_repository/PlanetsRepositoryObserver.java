package project.star_wars_universe.contracts.observer.planets_repository;

import project.star_wars_universe.models.planets.Planet;

/**
 * Represents a contract for observing changes in the {@link project.star_wars_universe.data.repository.PlanetsRepository}.
 * Implementations of this interface can be notified when a change occurs in the state of the {@link project.star_wars_universe.data.repository.PlanetsRepository}.
 */
public interface PlanetsRepositoryObserver {
    /**
     * Called when a {@link Planet}  is added to the {@link project.star_wars_universe.data.repository.PlanetsRepository}.
     * @param planet the new planet that is added to the planets' repository.
     */
    void onPlanetAdded(Planet planet);

    /**
     * Called when a {@link Planet} is removed from the {@link project.star_wars_universe.data.repository.PlanetsRepository}.
     * @param planet the planet that is removed from the planets' repository.
     */
    void onPlanetRemoved(Planet planet);
}
