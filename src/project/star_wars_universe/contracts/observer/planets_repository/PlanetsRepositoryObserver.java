package project.star_wars_universe.contracts.observer.planets_repository;

import project.star_wars_universe.models.planets.Planet;

public interface PlanetsRepositoryObserver {
    void onPlanetAdded(Planet planet);
    void onPlanetRemoved(Planet planet);
}
