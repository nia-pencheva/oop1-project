package project.star_wars_universe.util.comparators;

import project.star_wars_universe.models.jedi.Jedi;

import java.util.Comparator;

/**
 * A {@link Comparator} implementation which imposes an order
 * based on the {@link Jedi#rank} field first (ascending) and
 * the {@link Jedi#name} field second (lexicographically, ascending)
 * on a collection of {@link Jedi}.
 */
public class JediByRankAndName implements Comparator<Jedi> {

    /**
     * Compares two {@link Jedi} objects based on their {@link Jedi#rank} fields first
     * and their {@link Jedi#name} fields second. The {@link Jedi#rank} fields, which are values from the
     * {@link project.star_wars_universe.models.jedi.enums.Rank} enumeration, are compared based on
     * their ordinal values (their position in the enum declaration) by default.
     * @param jedi1 the first jedi.
     * @param jedi2 the second jedi.
     * @return a negative integer, zero, or a positive integer if the first jedi's rank or rank and name
     * are less than, equal to, or greater than the second jedi's rank or rank and name (lexicographically).
     */
    @Override
    public int compare(Jedi jedi1, Jedi jedi2) {
        int result = jedi1.getRank().compareTo(jedi2.getRank());

        if(result != 0) {
            return result;
        }

        return jedi1.getName().toLowerCase().compareTo(jedi2.getName().toLowerCase());
    }
}
