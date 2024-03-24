package project.star_wars_universe.contracts.util;

public interface Parser<T, S> {
    T parse(S content) throws Exception;
}
