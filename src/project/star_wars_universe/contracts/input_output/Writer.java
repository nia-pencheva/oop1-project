package project.star_wars_universe.contracts.input_output;

/**
 * Represents a contract for writing content.
 * Implementations of this interface are expected to define the specific
 * behaviour for writing content, such as writing to a file, console, etc.
 */
public interface Writer {
    /**
     * Writes the specified content.
     * @param content the content that should be written.
     * @throws Exception if an exception occurs during the writing process.
     */
    void write(String content) throws Exception;
}
