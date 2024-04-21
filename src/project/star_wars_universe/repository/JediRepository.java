package project.star_wars_universe.repository;

import project.star_wars_universe.contracts.repository.Repository;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.entities.planets.Planet;
import project.star_wars_universe.exceptions.jedi.JediAlreadyExistsException;

import java.util.HashSet;
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

    public Set<Jedi> getJedi() {
        return new HashSet<Jedi>(jedi);
    }

    public Jedi getJediByName(String name) {
        for(Jedi jedi : jedi) {
            if(jedi.getName() == name) {
                return jedi;
            }
        }

        return null;
    }

    @Override
    public void add(Jedi item) throws JediAlreadyExistsException {
        if(jedi.contains(item)) {
            throw new JediAlreadyExistsException();
        }

        jedi.add(item);
    }

    @Override
    public void remove(Jedi item) {
        jedi.remove(item);
    }

    @Override
    public void removeAll() {
        jedi.clear();
    }

    public void printJedi() {
        for(Jedi jedi : this.jedi) {
            System.out.println(jedi.toString());
        }
    }
}
