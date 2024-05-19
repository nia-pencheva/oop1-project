package project.star_wars_universe.models.jedi;

import project.star_wars_universe.exceptions.planets.PlanetDoesNotExistException;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.jedi.enums.SaberColor;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.jedi.*;
import project.star_wars_universe.data.repository.PlanetsRepository;

import java.util.Objects;

/**
 * Represents a jedi. This is one of the main entities of the application.
 */
public class Jedi {
    /**
     * The jedi's name. It is immutable and should not be null or empty.
     */
    private final String name;
    /**
     * The jedi's rank. It should not be null.
     */
    private Rank rank;
    /**
     * The jedi's age. It should be larger than 0 and smaller than 1000.
     */
    private int age;
    /**
     * The jedi's saber color. It should not be null.
     */
    private SaberColor saberColor;
    /**
     * The jedi's power. It should be larger than 0 and smaller than 1000.
     */
    private double power;

    /**
     * Initializes the Jedi instance. The jedi name is validated and
     * set in the constructor itself (it doesn't have a setter because it's immutable),
     * while the other fields are validated through their setter methods.
     * @param name the jedi's name
     * @param rank the jedi's rank
     * @param age the jedi's age
     * @param saberColor the jedi's saber color
     * @param power the jedi's power
     * @throws InvalidRankException if the value for rank is invalid.
     * @throws InvalidAgeException if the value for age is invalid.
     * @throws InvalidSaberColorException if the value for saber color is invalid.
     * @throws InvalidPowerException if the value for power is invalid.
     * @throws InvalidNameException if the value for name is invalid.
     */
    public Jedi(String name, Rank rank, int age, SaberColor saberColor, double power) throws InvalidRankException, InvalidAgeException, InvalidSaberColorException, InvalidPowerException, InvalidNameException {
        if(name.isEmpty() || Objects.isNull(name)) {
            throw new InvalidNameException();
        }
        this.name = name;
        setRank(rank);
        setAge(age);
        setSaberColor(saberColor);
        setPower(power);
    }

    /**
     * Gets the jedi's name.
     * @return the jedi's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the jedi's rank.
     * @return the jedi's rank.
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * Validates and sets the jedi's rank.
     * @param rank the jedi's rank.
     * @throws InvalidRankException if the value for rank is null.
     */
    public void setRank(Rank rank) throws InvalidRankException {
        if(rank == null) {
            throw new InvalidRankException();
        }

        this.rank = rank;
    }

    /**
     * Gets the jedi's age.
     * @return the jedi's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the jedi's age.
     * @param age the jedi's age.
     * @throws InvalidAgeException if the value for age is less than 0 or more than 1000.
     */
    public void setAge(int age) throws InvalidAgeException {
        if(age < 0 || age > 1000) {
            throw new InvalidAgeException();
        }

        this.age = age;
    }

    /**
     * Gets the jedi's saber color.
     * @return the jedi's saber color.
     */
    public SaberColor getSaberColor() {
        return saberColor;
    }

    /**
     * Sets the jedi's saber color
     * @param saberColor the jedi's saber color.
     * @throws InvalidSaberColorException if the jedi's saber color is null.
     */
    public void setSaberColor(SaberColor saberColor) throws InvalidSaberColorException {
        if(saberColor == null) {
            throw new InvalidSaberColorException();
        }
        this.saberColor = saberColor;
    }

    /**
     * Gets the jedi's power.
     * @return the jedi's power.
     */
    public double getPower() {
        return power;
    }

    /**
     * Sets the jedi's power.
     * @param power the jedi's power.
     * @throws InvalidPowerException if the jedi's power is less than 0 or more than 1000.
     */
    public void setPower(double power) throws InvalidPowerException {
        if(power < 0 || power > 1000) {
            throw new InvalidPowerException();
        }

        this.power = power;
    }

    /**
     * Promotes the jedi. First, the Rank instance corresponding to the next rank order is extracted.
     * Then it checks whether the promotion multiplier is larger than 0. If it is, the jedi's power is
     * set to [current_power + (current_power * multiplier)] and the jedi's rank is set to the next
     * one in the hierarchy.
     * @param multiplier the multiplier by which the jedi should be promoted.
     * @throws InvalidPromotionMultiplierException if the promotion multiplier is less than or equal to 0.
     * @throws HighestRankReachedException if the highest rank is reached for the jedi.
     * @throws InvalidPowerException if the power exceeds the allowed limits when it gets set to its new value.
     */
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

    /**
     * Demotes the jedi. First, the Rank instance corresponding to the previous rank order is extracted.
     * Then it checks whether the promotion multiplier is larger than 0. If it is, the jedi's power is set
     * to [current_power - (current_power * multiplier)] and the jedi's rank is set to the previous one
     * in the hierarchy.
     * @param multiplier the multiplier by which the jedi should be demoted.
     * @throws InvalidPromotionMultiplierException if the promotion multiplier is less than or equal to 0.
     * @throws LowestRankReachedException if the lowest rank is reached for the jedi.
     * @throws InvalidPowerException if the power exceeds the allowed limits when it gets set to its new value.
     */
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

    /**
     * Generates a string which is appropriate for displaying the jedi information.
     * @return a string appropriate for displaying the jedi information.
     */
    @Override
    public String toString() {
        String lineSeparator = System.getProperty("line.separator");
        Planet planet = null;

        try {
            planet = PlanetsRepository.getInstance().getPlanetByJedi(this);
        }
        catch(PlanetDoesNotExistException ex) {}


        StringBuilder builder = new StringBuilder();
        builder.append("Jedi Information: " + lineSeparator);
        builder.append("-----------------" + lineSeparator);
        builder.append("Name: " + name + lineSeparator);
        builder.append("Rank: " + rank.getDisplayName() + lineSeparator);
        builder.append("Age: " + age + lineSeparator);
        builder.append("Saber Color: " + saberColor.getColor() + lineSeparator);
        builder.append("Power: " + power + lineSeparator);
        builder.append("Planet: " + ((planet != null) ? planet.getName() : "Unknown"));

        return builder.toString();
    }

    /**
     * Compares the current jedi instance to another object.
     * @param o the object to which the current one is compared.
     * @return a boolean signifying whether the two objects are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jedi jedi = (Jedi) o;
        return Objects.equals(getName(), jedi.getName());
    }

    /**
     * Generates a hash code for the jedi instance based on the jedi's name.
     * @return a hash code for the jedi instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
