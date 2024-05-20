package project.star_wars_universe.util.comparators;

import project.star_wars_universe.models.jedi.Jedi;

import java.util.Comparator;

/**
 * A {@link Comparator} implementation which imposes an order
 * based on the {@link Jedi#age} field first (ascending) and the {@link Jedi#name}
 * field second (lexicographically, ascending) on a collection of {@link Jedi}.
 */
public class JediByAgeAndName implements Comparator<Jedi> {

    /**
     * Compares two {@link Jedi} objects based on their {@link Jedi#age} fields first
     * and their {@link Jedi#name} fields second.
     * @param jedi1 the first jedi.
     * @param jedi2 the second jedi.
     * @return a negative integer, zero, or a positive integer if the first jedi's age or age and name
     * is less than, equal to, or greater than the second jedi's age or age and name (lexicographically).
     */
    @Override
    public int compare(Jedi jedi1, Jedi jedi2) {
        int result = Integer.valueOf(jedi1.getAge()).compareTo(Integer.valueOf(jedi2.getAge()));

        if(result != 0) {
            return result;
        }

        return jedi1.getName().toLowerCase().compareTo(jedi2.getName().toLowerCase());
    }
}
