package project.star_wars_universe.contracts.util;

import project.star_wars_universe.data.AppData;
import project.star_wars_universe.exceptions.util.SerializationFailureException;

/**
 * Describes a contract for a data serializer. The classes implementing this interface
 * should define the specifics of serializing an {@link AppData} object into another
 * format when that data is supposed to be written to some resource (e.g. a file).
 * @param <T> the type of the data that should be serialized.
 */
public interface Serializer<T> {
    /**
     * Converts an {@link AppData} object into another format.
     * @param data the application data that should be serialized.
     * @return the serialized data in the specified format.
     * @throws SerializationFailureException if an error occurs during the serialization process.
     */
    T serialize(AppData data) throws SerializationFailureException;
}
