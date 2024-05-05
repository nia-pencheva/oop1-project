package project.star_wars_universe.util.comparators.jedi;

import project.star_wars_universe.models.jedi.Jedi;

import java.util.Comparator;

public class JediByName implements Comparator<Jedi> {
    @Override
    public int compare(Jedi jedi1, Jedi jedi2) {
        return jedi1.getName().toLowerCase().compareTo(jedi2.getName().toLowerCase());
    }
}
