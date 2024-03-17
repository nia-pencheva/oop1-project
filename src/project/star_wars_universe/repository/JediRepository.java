package project.star_wars_universe.repository;

import project.star_wars_universe.contracts.repository.Repository;
import project.star_wars_universe.entities.jedi.Jedi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JediRepository implements Repository<Jedi> {
    private static JediRepository instance = null;
    private Set<Jedi> jedi = new HashSet<>();

    private JediRepository() {}

    public static JediRepository getInstance() {
        if(instance == null) {
            instance = new JediRepository();
        }

        return instance;
    }

    public List<Jedi> getJedi() {
        return new ArrayList<Jedi>(jedi);
    }

    @Override
    public void add(Jedi item) {
        jedi.add(item);
    }

    @Override
    public void remove(Jedi item) {
        jedi.remove(item);
    }

    public void printJedi() {
        for(Jedi jedi : this.jedi) {
            System.out.println(jedi.toString());
        }
    }
}
