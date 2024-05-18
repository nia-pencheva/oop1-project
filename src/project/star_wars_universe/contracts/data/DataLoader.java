package project.star_wars_universe.contracts.data;

import project.star_wars_universe.exceptions.data.DataLoadingException;

/**
 * Describes a contract for loading data into and
 * unloading data from the application.
 * @param <T> the type of data that should be loaded into the application.
 */
public interface DataLoader<T> {
    /**
     * Loads data into the application.
     * @param data the data that should be loaded into the application.
     * @throws DataLoadingException if the data cannot be successfully loaded.
     */
    void load(T data) throws DataLoadingException;

    /**
     * Unloads data from the application.
     */
    void unload();
}
