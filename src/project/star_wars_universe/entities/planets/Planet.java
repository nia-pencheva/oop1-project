package project.star_wars_universe.entities.planets;

import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.exceptions.jedi.JediDoesNotExistException;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;
import project.star_wars_universe.repository.JediRepository;

import java.util.HashSet;
import java.util.Set;

public class Planet {
    private final String name;
    private Set<Jedi> jediPopulation = new HashSet<>();

    public Planet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean jediExists(Jedi jedi) {
        return jediPopulation.contains(jedi);
    }

    public void addJedi(Jedi jedi) throws JediDoesNotExistException, JediExistsOnThisPlanetException {
        if(!JediRepository.getInstance().jediExists(jedi)) {
            throw new JediDoesNotExistException();
        }

        if(jediExists(jedi)) {
            throw new JediExistsOnThisPlanetException();
        }

        jediPopulation.add(jedi);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                '}';
    }
}
