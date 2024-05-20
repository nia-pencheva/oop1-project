package project.star_wars_universe.data.repository;

import project.star_wars_universe.contracts.data.Repository;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.exceptions.jedi.JediAlreadyExistsException;
import project.star_wars_universe.exceptions.jedi.JediDoesNotExistException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Stores the {@link Jedi} entities of the application. It is implemented using
 * the singleton pattern because there should only be one place in the application
 * where the {@link Jedi} entities are stored - otherwise unexpected behaviours may occur
 * (e.g. data loss).
 */
public class JediRepository implements Repository<Jedi> {
    /**
     * The {@link JediRepository} instance.
     */
    private static JediRepository instance = null;
    /**
     * The {@code Set} of {@link Jedi} entities of the application.
     * It is a {@code Set} and not a {@code List} because there should
     * be no duplicate jedi.
     */
    private Set<Jedi> jedi = new HashSet<>();

    /**
     * Initializes the {@link JediRepository} instance. The constructor is private in accordance to the rules
     * for implementing the singleton pattern.
     */
    private JediRepository() {}

    /**
     * Gets the {@link JediRepository} instance. Here, the lazy loading approach is used - the
     * singleton instance is only created when it is first accessed.
     * @return the {@link JediRepository} instance.
     */
    public static JediRepository getInstance() {
        if(instance == null) {
            instance = new JediRepository();
        }

        return instance;
    }

    /**
     * Checks whether a jedi exists in the repository.
     * @param item the {@link Jedi} whose existence is being checked for.
     * @return a boolean value signifying whether the jedi exists in the repository.
     */
    public boolean jediExists(Jedi item) {
        return jedi.contains(item);
    }

    /**
     * Gets a {@code List} of the {@link Jedi} entities in the repository. This method
     * does not return the {@link JediRepository#jedi} list directly, but instead
     * it returns a new {@code ArrayList} initialized with the values
     * of the {@link JediRepository#jedi} list so that it cannot be
     * directly manipulated from the outside.
     * @return a {@code List} of the {@link Jedi} entities in the repository
     */
    public List<Jedi> getJedi() {
        return new ArrayList<>(jedi);
    }

    /**
     * Gets a jedi from the repository by a specified name.
     * @param name the name of the jedi that is searched for.
     * @return the jedi that is found.
     * @throws JediDoesNotExistException if no jedi with the specified name exists in the repository.
     */
    public Jedi getJediByName(String name) throws JediDoesNotExistException {
        for(Jedi jedi : jedi) {
            if(jedi.getName().equals(name)) {
                return jedi;
            }
        }

        throw new JediDoesNotExistException();
    }

    /**
     * Adds a jedi to the repository.
     * @param item the jedi that should be added.
     * @throws JediAlreadyExistsException if the specified jedi already exists in the repository.
     */
    @Override
    public void add(Jedi item) throws JediAlreadyExistsException {
        if(jedi.contains(item)) {
            throw new JediAlreadyExistsException();
        }

        jedi.add(item);
    }

    /**
     * Removes a jedi from the repository.
     * @param item the jedi that should be removed.
     */
    @Override
    public void remove(Jedi item) {
        jedi.remove(item);
    }

    /**
     * Removes all jedi from the repository.
     */
    @Override
    public void removeAll() {
        jedi.clear();
    }
}
