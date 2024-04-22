package project.star_wars_universe.entities.planets;

import project.star_wars_universe.exceptions.planets.JediDoesNotExistOnThisPlanetException;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Planet {
    private final String name;
    private Set<String> jediPopulation = new HashSet<>();

    public Planet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<String> getJediPopulation() {
        return new HashSet<>(jediPopulation);
    }

    public boolean jediExists(String jedi) {
        return jediPopulation.contains(jedi);
    }

    public void addJedi(String jedi) throws JediExistsOnThisPlanetException {
        if(jediExists(jedi)) {
            throw new JediExistsOnThisPlanetException();
        }

        jediPopulation.add(jedi);
    }

    public void removeJedi(String jedi) throws JediDoesNotExistOnThisPlanetException {
        if(!jediExists(jedi)) {
            throw new JediDoesNotExistOnThisPlanetException();
        }

        jediPopulation.remove(jedi);
    }

    @Override
    public String toString() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("Planet Information:" + lineSeparator);
        builder.append("-------------------" + lineSeparator);
        builder.append("Name: " + name + lineSeparator);
        builder.append("Jedi on this planet:" + lineSeparator);

        for(String jedi : jediPopulation) {
            builder.append("- " + jedi + lineSeparator);
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(getName(), planet.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
