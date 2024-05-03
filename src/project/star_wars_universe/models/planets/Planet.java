package project.star_wars_universe.models.planets;

import project.star_wars_universe.exceptions.planets.JediDoesNotExistOnThisPlanetException;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.util.comparators.jedi.JediByRankAndName;

import java.util.*;

public class Planet {
    private final String name;
    private Set<Jedi> jediPopulation = new HashSet<>();

    public Planet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Jedi> getJediPopulation() {
        return new HashSet<>(jediPopulation);
    }

    public boolean jediExists(Jedi jedi) {
        return jediPopulation.contains(jedi);
    }

    public void addJedi(Jedi jedi) throws JediExistsOnThisPlanetException {
        if(jediExists(jedi)) {
            throw new JediExistsOnThisPlanetException();
        }

        jediPopulation.add(jedi);
    }

    public void removeJedi(Jedi jedi) throws JediDoesNotExistOnThisPlanetException {
        if(!jediExists(jedi)) {
            throw new JediDoesNotExistOnThisPlanetException();
        }

        jediPopulation.remove(jedi);
    }

    public void sortJediPopulation(Comparator<Jedi> comparator) {
        Set<Jedi> sortedJedi = new TreeSet<>(comparator);
        sortedJedi.addAll(jediPopulation);
        jediPopulation = sortedJedi;
    }

    @Override
    public String toString() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append("Planet Information:" + lineSeparator);
        builder.append("-------------------" + lineSeparator);
        builder.append("Name: " + name + lineSeparator);
        builder.append("Jedi on this planet:");

        for(Jedi jedi : jediPopulation) {
            builder.append(lineSeparator);
            builder.append("- " + jedi.getName());
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
