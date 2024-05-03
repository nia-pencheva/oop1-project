package project.star_wars_universe.util.comparators.jedi;

import project.star_wars_universe.models.jedi.Jedi;

import java.util.Comparator;

public class JediByRankAndName implements Comparator<Jedi> {
    @Override
    public int compare(Jedi jedi1, Jedi jedi2) {
        int result = jedi1.getRank().compareTo(jedi2.getRank());

        if(result != 0) {
            return result;
        }

        return jedi1.getName().compareTo(jedi2.getName());
    }
}
