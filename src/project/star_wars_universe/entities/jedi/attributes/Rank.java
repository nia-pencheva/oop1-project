package project.star_wars_universe.entities.jedi.attributes;

public enum Rank {
    YOUNGLING("Youngling"),
    INITIATE("Initiate"),
    PADAWAN("Padwan"),
    KNIGHT_ASPIRANT("Knight Aspirant"),
    KNIGHT("Knight"),
    MASTER("Master"),
    BATTLE_MASTER("Battle Master"),
    GRAND_MASTER("Grand Master");

    private String rank;

    private Rank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
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
