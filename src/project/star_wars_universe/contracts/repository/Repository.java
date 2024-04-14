package project.star_wars_universe.contracts.repository;

import project.star_wars_universe.exceptions.jedi.JediAlreadyExistsException;

public interface Repository<T> {
    void add(T item) throws Exception;
    void remove(T item);
    void removeAll();
}
