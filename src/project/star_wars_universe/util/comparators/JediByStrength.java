package project.star_wars_universe.util.comparators;

import project.star_wars_universe.models.jedi.Jedi;

import java.util.Comparator;

/**
 * A {@link Comparator} implementation which imposes an order based on the {@link Jedi#power}
 * field on a collection of {@link Jedi}. The collection's items are sorted in an ascending order.
 */
public class JediByStrength implements Comparator<Jedi> {
    /**
     * Compares two {@link Jedi} objects based on their {@link Jedi#power} fields.
     * @param jedi1 the first jedi.
     * @param jedi2 the second jedi.
     * @return a negative integer, zero, or a positive integer if the first jedi's power is less
     * than, equal to, or greater tha the second jedi's power.
     */
    @Override
    public int compare(Jedi jedi1, Jedi jedi2) {
        return Double.valueOf(jedi1.getPower()).compareTo(Double.valueOf(jedi2.getPower()));
    }
}
