package project.star_wars_universe.entities.jedi.enums;

public enum Rank {
    YOUNGLING("youngling", "Youngling"),
    INITIATE("initiate", "Initiate"),
    PADAWAN("padwan", "padwan"),
    KNIGHT_ASPIRANT("knight_aspirant", "Knight Aspirant"),
    KNIGHT("knight", "Knight"),
    MASTER("master", "Master"),
    BATTLE_MASTER("battle_master", "Battle Master"),
    GRAND_MASTER("grand_master", "Grand Master");

    private String rank;
    private String displayName;

    private Rank(String rank, String displayName) {
        this.rank = rank;
        this.displayName = displayName;
    }

    public String getRank() {
        return rank;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Rank getValue(String rank) {
        for(Rank e: Rank.values()) {
            if(e.rank.equals(rank)) {
                return e;
            }
        }
        return null;
    }
}
