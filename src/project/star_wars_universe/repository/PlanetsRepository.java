package project.star_wars_universe.repository;

import project.star_wars_universe.contracts.repository.Repository;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.planets.PlanetAlreadyExistsException;
import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlanetsRepository implements Repository<Planet> {
    private static PlanetsRepository instance = null;
    private Set<Planet> planets = new HashSet<>();

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

    public Planet getPlanetByJediName(String jedi) {
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
    }

    @Override
    public void remove(Planet item) {
        planets.remove(item);
    }

    @Override
    public void removeAll() {
        planets.clear();
    }

    public void printPlanets() {
        for(Planet planet : this.planets) {
            System.out.println(planet.toString());
        }
    }
}
