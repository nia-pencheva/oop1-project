package project.star_wars_universe.contracts.observer.planets_repository;

import project.star_wars_universe.models.planets.Planet;

public interface PlanetsRepositorySubject {
    void addObserver(PlanetsRepositoryObserver observer);
    void removeObserver(PlanetsRepositoryObserver observer);
    void notifyPlanetAdded(Planet planet);
    void notifyPlanetRemoved(Planet planet);
}
