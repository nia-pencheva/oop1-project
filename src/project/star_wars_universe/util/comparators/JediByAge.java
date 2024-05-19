package project.star_wars_universe.util.comparators;

import project.star_wars_universe.models.jedi.Jedi;

import java.util.Comparator;

/**
 * A {@link Comparator} implementation which imposes an order
 * based on the {@link Jedi#age} field on a collection of {@link Jedi}.
 * The collection's items are sorted in ascending order.
 */
public class JediByAge implements Comparator<Jedi> {

    /**
     * Compares two {@link Jedi} objects based on their {@link Jedi#age} fields.
     * @param jedi1 the first jedi.
     * @param jedi2 the second jedi.
     * @return a negative integer, zero, or a positive integer if the first jedi's age
     * is less than, equal to, or greater than the second jedi's age.
     */
    @Override
    public int compare(Jedi jedi1, Jedi jedi2) {
        return Integer.valueOf(jedi1.getAge()).compareTo(Integer.valueOf(jedi2.getAge()));
    }
}
