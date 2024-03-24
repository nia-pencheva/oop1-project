package project.star_wars_universe.contracts.util;

public interface Serializer<T, S> {
    T serialize(S data) throws Exception;
}
