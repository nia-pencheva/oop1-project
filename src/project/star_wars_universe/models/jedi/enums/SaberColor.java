package project.star_wars_universe.models.jedi.enums;

import project.star_wars_universe.exceptions.jedi.InvalidSaberColorException;

public enum SaberColor {
    BLUE("blue"),
    GREEN("green"),
    PURPLE("purple"),
    YELLOW("yellow"),
    ORANGE("orange"),
    WHITE("white");

    private String color;

    SaberColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public static SaberColor getValue(String color) throws InvalidSaberColorException {
        for(SaberColor e: SaberColor.values()) {
            if(e.color.equals(color)) {
                return e;
            }
        }

        throw new InvalidSaberColorException();
    }
}
