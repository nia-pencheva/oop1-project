package project.star_wars_universe.contracts.repository;

public interface Repository<T> {
    void add(T item);
    void remove(T item);
    void removeAll();
}
