package project.star_wars_universe.models.planets;

import project.star_wars_universe.exceptions.planets.InvalidNameException;
import project.star_wars_universe.exceptions.planets.JediDoesNotExistOnThisPlanetException;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;
import project.star_wars_universe.models.jedi.Jedi;

import java.util.*;

/**
 * Represents a planet. This is one of the main entities of the application.
 */
public class Planet {
    /**
     * The name of the planet. It is immutable and should not be null or empty.
     */
    private final String name;
    /**
     * A <code>Set</code> of the {@link Jedi} populating the planet.
     */
    private Set<Jedi> jediPopulation = new HashSet<>();

    /**
     * Initializes the Planet instance. The planet name is validated and set
     * in the constructor itself (it doesn't have a setter because it's immutable).
     * @param name the planet's name.
     */
    public Planet(String name) throws InvalidNameException {
        if(name.isEmpty() || Objects.isNull(name)) {
            throw new InvalidNameException();
        }
        this.name = name;
    }

    /**
     * Gets the planet's name.
     * @return the planet's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the planet's jedi population. The {@link Planet#jediPopulation} is not directly
     * returned in order to prevent it from being modified. Instead, it is passed to
     * a new <code>HashSet</code>.
     * @return
     */
    public Set<Jedi> getJediPopulation() {
        return new HashSet<>(jediPopulation);
    }

    /**
     * Checks if a jedi exists in the planet's jedi population.
     * @param jedi the jedi which is searched for.
     * @return a boolean signifying whether the jedi was found or not.
     */
    public boolean jediExists(Jedi jedi) {
        return jediPopulation.contains(jedi);
    }

    /**
     * Adds a jedi to the planet's jedi population.
     * @param jedi the jedi to be added.
     * @throws JediExistsOnThisPlanetException if the jedi already exists on the planet.
     */
    public void addJedi(Jedi jedi) throws JediExistsOnThisPlanetException {
        if(jediExists(jedi)) {
            throw new JediExistsOnThisPlanetException();
        }

        jediPopulation.add(jedi);
    }

    /**
     * Removes a jedi from the planet's jedi population.
     * @param jedi the jedi to be removed.
     * @throws JediDoesNotExistOnThisPlanetException if the jedi does not exist on the planet.
     */
    public void removeJedi(Jedi jedi) throws JediDoesNotExistOnThisPlanetException {
        if(!jediExists(jedi)) {
            throw new JediDoesNotExistOnThisPlanetException();
        }

        jediPopulation.remove(jedi);
    }

    /**
     * Sorts the jedi population with the help of a specified comparator.
     * The current {@link Planet#jediPopulation} is added to a <code>TreeSet</code>
     * initialized with the specified comparator, which is then assigned
     * to the {@link Planet#jediPopulation} field.
     * @param comparator
     */
    public void sortJediPopulation(Comparator<Jedi> comparator) {
        Set<Jedi> sortedJedi = new TreeSet<>(comparator);
        sortedJedi.addAll(jediPopulation);
        jediPopulation = sortedJedi;
    }

    /**
     * Generates a string which is appropriate for displaying the planet information.
     * @return a string appropriate for displaying the planet information.
     */
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

    /**
     * Compares the current planet instance to another object.
     * @param o the object to which the current one is compared.
     * @return a boolean signifying whether the two objects are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(getName(), planet.getName());
    }

    /**
     * Generates a hash code for the planet instance based on the planets's name.
     * @return a hash code for the planet instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
