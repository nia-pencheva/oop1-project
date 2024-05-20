package project.star_wars_universe.models.jedi.enums;

import project.star_wars_universe.exceptions.jedi.InvalidSaberColorException;

/**
 * Contains all saber colors.
 */
public enum SaberColor {
    BLUE("blue"),
    GREEN("green"),
    PURPLE("purple"),
    YELLOW("yellow"),
    ORANGE("orange"),
    WHITE("white");

    /**
     * The name which is displayed when printing information
     * and also by which a {@link SaberColor} instance can be indicated by the user.
     */
    private String color;

    /**
     * Initializes the {@link SaberColor} instance with its name.
     * @param color the name which can be displayed when printing information and also by which a {@link SaberColor} instance can be indicated by the user.
     */
    SaberColor(String color) {
        this.color = color;
    }

    /**
     * Gets the name which is displayed when printing information
     * and also by which a {@link SaberColor} instance can be indicated by the user.
     * @return the saber color name.
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the SaberColor instance corresponding to a specified name.
     * @param color the saber color name
     * @return the relevant {@link SaberColor} instance.
     * @throws InvalidSaberColorException if no saber color with that name exists.
     */
    public static SaberColor getValue(String color) throws InvalidSaberColorException {
        for(SaberColor e: SaberColor.values()) {
            if(e.color.equals(color)) {
                return e;
            }
        }

        throw new InvalidSaberColorException();
    }
}
