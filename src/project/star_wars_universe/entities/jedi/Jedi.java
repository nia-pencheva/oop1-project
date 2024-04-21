package project.star_wars_universe.entities.jedi;

import project.star_wars_universe.entities.jedi.enums.Rank;
import project.star_wars_universe.entities.jedi.enums.SaberColor;
import project.star_wars_universe.exceptions.jedi.InvalidAgeException;
import project.star_wars_universe.exceptions.jedi.InvalidPowerException;
import project.star_wars_universe.exceptions.jedi.InvalidRankException;
import project.star_wars_universe.exceptions.jedi.InvalidSaberColorException;

import java.util.Objects;

public class Jedi {
    private final String name;
    private Rank rank;
    private int age;
    private SaberColor saberColor;
    private double power;

    public Jedi(String name, String rank, int age, String saberColor, double power) throws InvalidRankException, InvalidAgeException, InvalidSaberColorException, InvalidPowerException {
        this.name = name;
        setRank(rank);
        setAge(age);
        setSaberColor(saberColor);
        setPower(power);
    }

    public String getName() {
        return name;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(String rank) throws InvalidRankException {
        Rank rankEnumVal = Rank.getValue(rank);

        if(rankEnumVal == null) {
            throw new InvalidRankException();
        }

        this.rank = rankEnumVal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws InvalidAgeException {
        if(age < 0 || age > 1000) {
            throw new InvalidAgeException();
        }

        this.age = age;
    }

    public SaberColor getSaberColor() {
        return saberColor;
    }

    public void setSaberColor(String saberColor) throws InvalidSaberColorException {
        SaberColor saberColorEnumVal = SaberColor.getValue(saberColor);

        if(saberColorEnumVal == null) {
            throw new InvalidSaberColorException();
        }

        this.saberColor = saberColorEnumVal;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) throws InvalidPowerException {
        if(power < 0 || power > 1000) {
            throw new InvalidPowerException();
        }

        this.power = power;
    }

    @Override
    public String toString() {
        return "Jedi{" +
                "name='" + name + '\'' +
                ", rank=" + rank +
                ", age=" + age +
                ", saberColor=" + saberColor +
                ", power=" + power +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jedi jedi = (Jedi) o;
        return Objects.equals(getName(), jedi.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
