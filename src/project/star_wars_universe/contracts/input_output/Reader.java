package project.star_wars_universe.contracts.input_output;

/**
 * Represents a contract for reading content.
 * Implementations of this interface are expected to define the specific
 * behaviour for reading content, such as reading from a file, console etc.
 */
public interface Reader {
    /**
     * Reads the content.
     * @return the content that was read.
     * @throws Exception if an error occurs during the reading process.
     */
    String read() throws Exception;
}
