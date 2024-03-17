package project.star_wars_universe.entities.planets;

public class Planet {
    private final String name;

    public Planet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                '}';
    }
}
