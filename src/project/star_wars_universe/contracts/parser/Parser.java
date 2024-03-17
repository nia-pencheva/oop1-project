package project.star_wars_universe.contracts.parser;

public interface Parser<T> {
    T parse(String content);
}
