package project.star_wars_universe.contracts.util;

import project.star_wars_universe.data.AppData;
import project.star_wars_universe.exceptions.util.ParsingFailureException;

/**
 * Describes a contract for a parsing data. The classes implementing this interface
 * should define the specifics of parsing data gathered from a resource (e.g. a file)
 * into an {@link AppData} object.
 * @param <T> the type of the data that should be parsed.
 */
public interface Parser<T> {
    /**
     * Converts some type of data into an {@link AppData} object.
     * @param content the content that should be parsed.
     * @return the parsed application data.
     * @throws ParsingFailureException if an error occurs during the parsing process.
     */
    AppData parse(T content) throws ParsingFailureException;
}
