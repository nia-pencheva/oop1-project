package project.star_wars_universe.models.jedi;

import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.jedi.enums.SaberColor;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.jedi.*;
import project.star_wars_universe.repository.PlanetsRepository;

import java.util.Objects;

public class Jedi {
    private final String name;
    private Rank rank;
    private int age;
    private SaberColor saberColor;
    private double power;

    public Jedi(String name, Rank rank, int age, SaberColor saberColor, double power) throws InvalidRankException, InvalidAgeException, InvalidSaberColorException, InvalidPowerException {
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

    public void setRank(Rank rank) {
        this.rank = rank;
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

    public void setSaberColor(SaberColor saberColor) {
        this.saberColor = saberColor;
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

    public void promoteJedi(double multiplier) throws InvalidPromotionMultiplierException, HighestRankReachedException, InvalidPowerException {
        try {
            Rank nextRank = Rank.getRankByOrder(rank.getRankOrder() + 1);

            if(multiplier <= 0) {
                throw new InvalidPromotionMultiplierException();
            }

            setPower(power + (power * multiplier));
            setRank(nextRank);
        }
        catch(InvalidRankException ex) {
            throw new HighestRankReachedException();
        }
    }

    public void demoteJedi(double multiplier) throws InvalidPromotionMultiplierException, LowestRankReachedException, InvalidPowerException {
        try {
            Rank previousRank = Rank.getRankByOrder(rank.getRankOrder() - 1);

            if(multiplier <= 0) {
                throw new InvalidPromotionMultiplierException();
            }

            setPower(power - (power * multiplier));
            setRank(previousRank);
        }
        catch(InvalidRankException ex) {
            throw new LowestRankReachedException();
        }
    }

    @Override
    public String toString() {
        String lineSeparator = System.getProperty("line.separator");
        Planet planet = PlanetsRepository.getInstance().getPlanetByJediName(name);

        StringBuilder builder = new StringBuilder();
        builder.append("Jedi Information: " + lineSeparator);
        builder.append("-----------------" + lineSeparator);
        builder.append("Name: " + name + lineSeparator);
        builder.append("Rank: " + rank.getDisplayName() + lineSeparator);
        builder.append("Age: " + age + lineSeparator);
        builder.append("Saber Color: " + saberColor.getColor() + lineSeparator);
        builder.append("Power: " + power + lineSeparator);
        builder.append("Planet: " + ((planet != null) ? planet.getName() : "Unknown") + lineSeparator);

        return builder.toString();
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
