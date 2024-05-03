package project.star_wars_universe.models.jedi.enums;

import project.star_wars_universe.exceptions.jedi.InvalidRankException;

public enum Rank {
    YOUNGLING(1, "youngling", "Youngling"),
    INITIATE(2, "initiate", "Initiate"),
    PADAWAN(3, "padwan", "padwan"),
    KNIGHT_ASPIRANT(4, "knight_aspirant", "Knight Aspirant"),
    KNIGHT(5, "knight", "Knight"),
    MASTER(6, "master", "Master"),
    BATTLE_MASTER(7, "battle_master", "Battle Master"),
    GRAND_MASTER(8,"grand_master", "Grand Master");

    private int rankOrder;
    private String rank;
    private String displayName;

    private Rank(int rankOrder, String rank, String displayName) {
        this.rankOrder = rankOrder;
        this.rank = rank;
        this.displayName = displayName;
    }

    public int getRankOrder() {
        return rankOrder;
    }

    public String getRank() {
        return rank;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Rank getValue(String rank) throws InvalidRankException {
        for(Rank e: Rank.values()) {
            if(e.rank.equals(rank)) {
                return e;
            }
        }

        throw new InvalidRankException();
    }

    public static Rank getRankByOrder(int rankOrder) throws InvalidRankException {
        for(Rank e: Rank.values()) {
            if(e.rankOrder == rankOrder) {
                return e;
            }
        }

        throw new InvalidRankException();
    }


}
