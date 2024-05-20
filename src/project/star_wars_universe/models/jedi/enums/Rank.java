package project.star_wars_universe.models.jedi.enums;

import project.star_wars_universe.exceptions.jedi.InvalidRankException;

/**
 * Contains all jedi ranks.
 */
public enum Rank {
    YOUNGLING(1, "youngling", "Youngling"),
    INITIATE(2, "initiate", "Initiate"),
    PADAWAN(3, "padawan", "padwan"),
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
     * The name by which a {@link Rank} instance can be indicated by the user.
     */
    private String rank;
    /**
     * The formatted name appropriate for being displayed.
     */
    private String displayName;

    /**
     * Initializes the {@link Rank} instance.
     * @param rankOrder the order of the rank in the hierarchy.
     * @param rank the name by which a {@link Rank} instance can be indicated by the user.
     * @param displayName the formatted name appropriate for being displayed.
     */
    private Rank(int rankOrder, String rank, String displayName) {
        this.rankOrder = rankOrder;
        this.rank = rank;
        this.displayName = displayName;
    }

    /**
     * Gets the rank's order in the hierarchy.
     * @return the rank's order in the hierarchy.
     */
    public int getRankOrder() {
        return rankOrder;
    }

    /**
     * Gets the name by which a {@link Rank} instance can be indicated by the user
     * @return the name by which a {@link Rank} instance can be indicated by the user.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Gets the rank's display name.
     * @return the rank's display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the {@link Rank} instance corresponding to a specified name.
     * @param rank the specified rank name.
     * @return the relevant {@link Rank} instance.
     * @throws InvalidRankException if no rank with that name exists.
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
     * @return the relevant {@link Rank} instance.
     * @throws InvalidRankException if no rank with that order number exists.
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
