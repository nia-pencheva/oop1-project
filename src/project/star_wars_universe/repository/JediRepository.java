package project.star_wars_universe.repository;

import project.star_wars_universe.contracts.repository.Repository;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.exceptions.jedi.JediAlreadyExistsException;
import project.star_wars_universe.exceptions.jedi.JediDoesNotExistException;

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
        return new ArrayList<>(jedi);
    }

    public Jedi getJediByName(String name) throws JediDoesNotExistException {
        for(Jedi jedi : jedi) {
            if(jedi.getName().equals(name)) {
                return jedi;
            }
        }

        throw new JediDoesNotExistException();
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
