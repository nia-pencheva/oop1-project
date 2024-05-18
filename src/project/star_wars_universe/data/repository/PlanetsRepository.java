package project.star_wars_universe.data.repository;

import project.star_wars_universe.contracts.observer.planets_repository.PlanetsRepositoryObserver;
import project.star_wars_universe.contracts.data.Repository;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.planets.PlanetAlreadyExistsException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;

import java.util.*;

public class PlanetsRepository implements Repository<Planet> {
    private static PlanetsRepository instance = null;
    private Set<Planet> planets = new HashSet<>();
    private List<PlanetsRepositoryObserver> observers = new ArrayList<>();

    private PlanetsRepository() {}

    public static PlanetsRepository getInstance() {
        if(instance == null) {
            instance = new PlanetsRepository();
        }

        return instance;
    }

    public List<Planet> getPlanets() {
        return new ArrayList<>(planets);
    }

    public Planet getPlanetByName(String name) throws PlanetDoesNotExistException {
        for(Planet planet : planets) {
            if(planet.getName().equals(name)) {
                return planet;
            }
        }

        throw new PlanetDoesNotExistException();
    }

    public Planet getPlanetByJedi(Jedi jedi) {
        for(Planet planet : planets) {
            if(planet.getJediPopulation().contains(jedi)) {
                return planet;
            }
        }
        return null;
    }

    @Override
    public void add(Planet item) throws PlanetAlreadyExistsException {
        if(planets.contains(item)) {
            throw new PlanetAlreadyExistsException();
        }
        planets.add(item);
        notifyPlanetAdded(item);
    }

    @Override
    public void remove(Planet item) {
        planets.remove(item);
        notifyPlanetRemoved(item);
    }

    @Override
    public void removeAll() {
        planets.clear();
    }

    public void addObserver(PlanetsRepositoryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PlanetsRepositoryObserver observer) {
        observers.remove(observer);
    }

    public void notifyPlanetAdded(Planet planet) {
        for(PlanetsRepositoryObserver observer : observers) {
            observer.onPlanetAdded(planet);
        }
    }

    public void notifyPlanetRemoved(Planet planet) {
        for(PlanetsRepositoryObserver observer : observers) {
            observer.onPlanetRemoved(planet);
        }
    }
}
