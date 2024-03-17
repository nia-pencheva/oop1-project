package project.star_wars_universe.entities.jedi;

import project.star_wars_universe.entities.jedi.attributes.Rank;
import project.star_wars_universe.entities.jedi.attributes.SaberColor;

public class Jedi {
    private final String name;
    private Rank rank;
    private int age;
    private SaberColor saberColor;
    private double power;
    private String planet;

    public Jedi(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SaberColor getSaberColor() {
        return saberColor;
    }

    public void setSaberColor(SaberColor saberColor) {
        this.saberColor = saberColor;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    @Override
    public String toString() {
        return "Jedi{" +
                "name='" + name + '\'' +
                ", rank=" + rank +
                ", age=" + age +
                ", saberColor=" + saberColor +
                ", power=" + power +
                ", planet='" + planet + '\'' +
                '}';
    }
}
