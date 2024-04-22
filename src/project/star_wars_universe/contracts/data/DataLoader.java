package project.star_wars_universe.contracts.data;

import project.star_wars_universe.exceptions.data.DataLoadingException;

public interface DataLoader<T> {
    void load(T data) throws DataLoadingException;
    void unload();
}
