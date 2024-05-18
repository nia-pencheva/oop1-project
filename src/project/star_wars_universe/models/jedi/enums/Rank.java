package project.star_wars_universe.models.jedi.enums;

import project.star_wars_universe.exceptions.jedi.InvalidRankException;

/**
 * Contains all jedi ranks.
 */
public enum Rank {
    YOUNGLING(1, "youngling", "Youngling"),
    INITIATE(2, "initiate", "Initiate"),
    PADAWAN(3, "padwan", "padwan"),
    KNIGHT_ASPIRANT(4, "knight_aspirant", "Knight Aspirant"),
    KNIGHT(5, "knight", "Knight"),
    MASTER(6, "master", "Master"),
    BATTLE_MASTER(7, "battle_master", "Battle Master"),
    GRAND_MASTER(8,"grand_master", "Grand Master");

    /**
     * The order of the rank in the hierarchy.
     */
    private int rankOrder;
    /**
     * The rank rank name by which a Rank instance can be indicated by the user.
     */
    private String rank;
    /**
     * The formatted name appropriate for being displayed.
     */
    private String displayName;

    /**
     * Initializes the Rank instance.
     * @param rankOrder the order of the rank in the hierarchy.
     * @param rank the rank name by which a Rank instance can be indicated by the user.
     * @param displayName the formatted name appropriate for being displayed.
     */
    private Rank(int rankOrder, String rank, String displayName) {
        this.rankOrder = rankOrder;
        this.rank = rank;
        this.displayName = displayName;
    }

    /**
     * Gets the rank order.
     * @return the rank order
     */
    public int getRankOrder() {
        return rankOrder;
    }

    /**
     * Gets the rank name by which a Rank instance can be indicated by the user.
     * @return the rank name.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Gets the rank display name.
     * @return the display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the Rank instance corresponding to a specified name.
     * @param rank the specified rank name.
     * @return the relevant Rank instance.
     * @throws InvalidRankException if no such rank exists.
     */
    public static Rank getValue(String rank) throws InvalidRankException {
        for(Rank e: Rank.values()) {
            if(e.rank.equals(rank)) {
                return e;
            }
        }

        throw new InvalidRankException();
    }

    /**
     * Gets the Rank instance corresponding to a specified rank order.
     * @param rankOrder the specified rank order.
     * @return the relevant Rank instance.
     * @throws InvalidRankException if no such rank exists.
     */
    public static Rank getRankByOrder(int rankOrder) throws InvalidRankException {
        for(Rank e: Rank.values()) {
            if(e.rankOrder == rankOrder) {
                return e;
            }
        }

        throw new InvalidRankException();
    }
}
