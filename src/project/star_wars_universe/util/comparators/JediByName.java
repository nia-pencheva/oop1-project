package project.star_wars_universe.util.comparators;

import project.star_wars_universe.models.jedi.Jedi;

import java.util.Comparator;

/**
 * A {@link Comparator} implementation which imposes an order
 * based on the {@link Jedi#name} field on a collection of {@link Jedi}.
 * The collection's items are sorted in ascending order (lexicographically).
 */
public class JediByName implements Comparator<Jedi> {

    /**
     * Compares two {@link Jedi} objects based on their {@link Jedi#name} fields.
     * @param jedi1 the first jedi.
     * @param jedi2 the second jedi.
     * @return a negative integer, zero, or a positive integer if the first jedi's name
     * is less than, equal to, or greater than the second jedi's name (lexicographically).
     */
    @Override
    public int compare(Jedi jedi1, Jedi jedi2) {
        return jedi1.getName().toLowerCase().compareTo(jedi2.getName().toLowerCase());
    }
}
