package project.star_wars_universe.contracts.util;

public interface Parser<T, S> {
    S parse(T content) throws Exception;
}
