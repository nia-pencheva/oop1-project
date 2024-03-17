package project.star_wars_universe.contracts.parser;

public interface Parser<T, S> {
    T parse(S content) throws Exception;
}
