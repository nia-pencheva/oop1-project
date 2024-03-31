package project.star_wars_universe.repository;

import project.star_wars_universe.contracts.repository.Repository;
import project.star_wars_universe.entities.planets.Planet;

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

    public Set<Planet> getPlanets() {
        return new HashSet<Planet>(planets);
    }

    @Override
    public void add(Planet item) {
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
