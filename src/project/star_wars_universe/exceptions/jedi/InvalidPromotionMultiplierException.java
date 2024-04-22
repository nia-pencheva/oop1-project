package project.star_wars_universe.exceptions.jedi;

public class InvalidPromotionMultiplierException extends Exception {
    public InvalidPromotionMultiplierException() {
        super("Multiplier must be a positive number");
    }
}
