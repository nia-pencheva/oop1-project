package project.star_wars_universe.input_output;

import project.star_wars_universe.contracts.input_output.Writer;

import java.io.IOException;

/**
 * Provides the functionality to write content to a file specified by its path.
 */
public class FileWriter implements Writer {
    /**
     * The path of the file that should be written to.
     */
    private String path;

    /**
     * Initializes the file writer with the path of the file that should be written to.
     * @param path the path of the file that should be written to.
     */
    public FileWriter(String path) {
        this.path = path;
    }

    /**
     * Writes content to the file specified by {@link FileWriter#path}.
     * A {@link java.io.FileWriter} is instantiated using {@link FileWriter#path}
     * and the specified content is written to the file. If there
     * is any existing data in the file prior to this operation, it is overwritten.
     * When the writing is done, the output stream is closed.
     * @param content the content that should be written.
     * @throws IOException if an exception occurs while the file is being written to.
     */
    @Override
    public void write(String content) throws IOException {
        java.io.FileWriter wr = new java.io.FileWriter(path);
        wr.write(content);
        wr.close();
    }
}
