package project.star_wars_universe.contracts.util;

public interface Serializer<T, S> {
    S serialize(T data) throws Exception;
}
