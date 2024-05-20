package project.star_wars_universe.data.repository;

import project.star_wars_universe.contracts.observer.planets_repository.PlanetsRepositoryObserver;
import project.star_wars_universe.contracts.data.Repository;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.planets.PlanetAlreadyExistsException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;

import java.util.*;

/**
 * Stores the {@link Planet} entities of the application. It is implemented using
 * the singleton pattern because there should only be one place in the application
 * where the {@link Planet} entities are stored - otherwise unexpected behaviours may occur
 * (e.g. data loss).
 */
public class PlanetsRepository implements Repository<Planet> {
    /**
     * The {@link PlanetsRepository} instance.
     */
    private static PlanetsRepository instance = null;
    /**
     * The {@code Set} of {@link Planet} entities of the application.
     * It is a {@code Set} and not a {@code List} because there should
     * be no duplicate planets.
     */
    private Set<Planet> planets = new HashSet<>();
    /**
     * A list of observers that should be notified of the changes
     * occurring in the repository. All observers of this class must
     * implement the {@link PlanetsRepositoryObserver} interface.
     */
    private List<PlanetsRepositoryObserver> observers = new ArrayList<>();

    /**
     * Initializes the class. The constructor is private in accordance to the
     * rules for implementing the singleton pattern.
     */
    private PlanetsRepository() {}

    /**
     * Gets the {@link PlanetsRepository} instance. Here, the lazy loading approach is used - the
     * singleton instance is only created when it is first accessed.
     * @return the {@link PlanetsRepository} instance.
     */
    public static PlanetsRepository getInstance() {
        if(instance == null) {
            instance = new PlanetsRepository();
        }

        return instance;
    }

    /**
     * Gets a {@code List} of the {@link Planet} entities in the repository.
     * This method does not return the {@link PlanetsRepository#planets} list directly, but
     * instead it returns a new {@code ArrayList} initialized with the values
     * of the {@link PlanetsRepository#planets} list so that it cannot be
     * directly manipulated from the outside.
     * @return a {@code List} of the {@link Planet} entities in the repository.
     */
    public List<Planet> getPlanets() {
        return new ArrayList<>(planets);
    }

    /**
     * Gets a planet from the repository by a specified name.
     * @param name the name of the planet that is searched for.
     * @return the planet that is found.
     * @throws PlanetDoesNotExistException if no planet with the specified name exists in the repository.
     */
    public Planet getPlanetByName(String name) throws PlanetDoesNotExistException {
        for(Planet planet : planets) {
            if(planet.getName().equals(name)) {
                return planet;
            }
        }

        throw new PlanetDoesNotExistException();
    }

    /**
     * Gets the planet that a specified jedi populates.
     * @param jedi the jedi whose planet is searched for.
     * @return the planet which the specified jedi populates.
     * @throws PlanetDoesNotExistException if the specified jedi does not populate any of the planets in the repository.
     */
    public Planet getPlanetByJedi(Jedi jedi) throws PlanetDoesNotExistException {
        for(Planet planet : planets) {
            if(planet.getJediPopulation().contains(jedi)) {
                return planet;
            }
        }

        throw new PlanetDoesNotExistException();
    }

    /**
     * Adds a planet to the repository. The {@link PlanetsRepository#observers} get notified.
     * @param item the planet that should be added.
     * @throws PlanetAlreadyExistsException if the specified planet already exists in the repository.
     */
    @Override
    public void add(Planet item) throws PlanetAlreadyExistsException {
        if(planets.contains(item)) {
            throw new PlanetAlreadyExistsException();
        }
        planets.add(item);
        notifyPlanetAdded(item);
    }

    /**
     * Removes a planet from the repository. The {@link PlanetsRepository#observers} get notified.
     * @param item the planet that should be removed.
     */
    @Override
    public void remove(Planet item) {
        planets.remove(item);
        notifyPlanetRemoved(item);
    }

    /**
     * Removes all planets from the repository.
     */
    @Override
    public void removeAll() {
        planets.clear();
    }

    /**
     * Adds an observer to the class.
     * @param observer the observer that should be added to the list of observers of the class.
     */
    public void addObserver(PlanetsRepositoryObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the class.
     * @param observer the observer that should be removed from the list of observers of the class.
     */
    public void removeObserver(PlanetsRepositoryObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies the observers of the repository that a planet has been added.
     * @param planet the planet that has been added to the repository.
     */
    public void notifyPlanetAdded(Planet planet) {
        for(PlanetsRepositoryObserver observer : observers) {
            observer.onPlanetAdded(planet);
        }
    }

    /**
     * Notifies the observers of the repository that a planet has been removed.
     * @param planet the planet that has been removed from the repository.
     */
    public void notifyPlanetRemoved(Planet planet) {
        for(PlanetsRepositoryObserver observer : observers) {
            observer.onPlanetRemoved(planet);
        }
    }
}
