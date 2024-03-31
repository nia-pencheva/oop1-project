package project.star_wars_universe.data;

import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.entities.planets.Planet;

import java.util.Set;

public class AppData {
    private Set<Jedi> jedi;
    private Set<Planet> planets;

    public AppData(Set<Jedi> jedi, Set<Planet> planets) {
        this.jedi = jedi;
        this.planets = planets;
    }

    public Set<Jedi> getJedi() {
        return jedi;
    }

    public Set<Planet> getPlanets() {
        return planets;
    }
}
