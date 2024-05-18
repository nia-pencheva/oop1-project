package project.star_wars_universe.util.comparators;

import project.star_wars_universe.models.jedi.Jedi;

import java.util.Comparator;

public class JediByAge implements Comparator<Jedi> {

    @Override
    public int compare(Jedi jedi1, Jedi jedi2) {
        return Integer.valueOf(jedi1.getAge()).compareTo(Integer.valueOf(jedi2.getAge()));
    }
}
