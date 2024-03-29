package project.star_wars_universe.entities.jedi.enums;

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

    public static SaberColor getValue(String color) {
        for(SaberColor e: SaberColor.values()) {
            if(e.color.equals(color)) {
                return e;
            }
        }
        return null;
    }
}
