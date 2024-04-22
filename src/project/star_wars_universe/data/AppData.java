package project.star_wars_universe.data;

import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.entities.planets.Planet;

import java.util.List;
import java.util.Set;

public class AppData {
    private List<Jedi> jedi;
    private List<Planet> planets;

    public AppData(List<Jedi> jedi, List<Planet> planets) {
        this.jedi = jedi;
        this.planets = planets;
    }

    public List<Jedi> getJedi() {
        return jedi;
    }

    public List<Planet> getPlanets() {
        return planets;
    }
}
