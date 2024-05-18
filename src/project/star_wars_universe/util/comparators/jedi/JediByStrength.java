package project.star_wars_universe.util.comparators.jedi;

import project.star_wars_universe.models.jedi.Jedi;

import java.util.Comparator;

public class JediByStrength implements Comparator<Jedi> {
    @Override
    public int compare(Jedi jedi1, Jedi jedi2) {
        return Double.valueOf(jedi1.getPower()).compareTo(Double.valueOf(jedi2.getPower()));
    }
}
