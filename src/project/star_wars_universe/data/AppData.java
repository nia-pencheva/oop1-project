package project.star_wars_universe.data;

import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;

import java.util.List;

/**
 * Represents a collection of all entities of the application. It serves as an
 * intermediate object representing the application data when data from an outside
 * resource should be parsed and loaded into the application or when the data
 * from the application should be serialized and written to an outside resource.
 */
public class AppData {
    /**
     * A list of the {@link Jedi} in the application.
     */
    private List<Jedi> jedi;
    /**
     * A list of the {@link Planet}s in the application.
     */
    private List<Planet> planets;

    /**
     * Initializes the instance with all the application data.
     * @param jedi list of the {@link Jedi} in the application.
     * @param planets list of the {@link Planet}s in the application.
     */
    public AppData(List<Jedi> jedi, List<Planet> planets) {
        this.jedi = jedi;
        this.planets = planets;
    }

    /**
     * Gets the list of {@link Jedi} in the application.
     * @return the list of {@link Jedi} in the application.
     */
    public List<Jedi> getJedi() {
        return jedi;
    }

    /**
     * Gets the list of {@link Planet}s in the application.
     * @return the list of {@link Planet}s in the application.
     */
    public List<Planet> getPlanets() {
        return planets;
    }
}
