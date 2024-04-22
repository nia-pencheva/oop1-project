package project.star_wars_universe.contracts.util;

import project.star_wars_universe.exceptions.util.SerializationFailureException;

public interface Serializer<T, S> {
    S serialize(T data) throws SerializationFailureException;
}
