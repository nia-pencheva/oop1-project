package project.star_wars_universe.contracts.data;

/**
 * Describes a contract for implementing a repository which stores data.
 * Implementations are expected to implement some sort of storage
 * mechanism (e.g. a {@link java.util.Collection} containing items of a certain data type)
 * from which they can add and remove items.
 * @param <T> the type of the data stored in the repository.
 */
public interface Repository<T> {
    /**
     * Adds an item to the repository.
     * @param item the item that should be added.
     * @throws Exception if an error occurs while adding the item.
     */
    void add(T item) throws Exception;

    /**
     * Removes an item from the repository.
     * @param item the item that should be removed.
     */
    void remove(T item);

    /**
     * Removes all items from the repository.
     */
    void removeAll();
}
