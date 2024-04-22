package project.star_wars_universe.contracts.util;

import project.star_wars_universe.exceptions.util.ParsingFailureException;

public interface Parser<T, S> {
    S parse(T content) throws ParsingFailureException;
}
