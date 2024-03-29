package project.star_wars_universe.contracts.data;

public interface DataLoader<T> {
    void load(T data) throws Exception;
    void unload();
}
