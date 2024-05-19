package project.star_wars_universe.input_output;

import project.star_wars_universe.contracts.input_output.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * Provides the functionality to read the contents of a file specified by its path.
 */
public class FileReader implements Reader {
    /**
     * The path of the file that should be read.
     */
    private String path;

    /**
     * Initializes a file reader with the path of the file that should be read.
     * @param path the path of the file that should be read.
     */
    public FileReader(String path) {
        this.path = path;
    }

    /**
     * Reads the contents of the file specified by {@link FileReader#path}.
     * A new {@link File} object is created using the specified {@link FileReader#path}.
     * If the file specified does not exist, it is created.
     * Then, using a {@link BufferedReader} and the {@link java.io.FileReader} class
     * the content is read and appended to a {@link StringBuilder} instance.
     * When the reading process is finished, the input stream is closed
     * and the content is returned as a {@code String}.
     * @return the content of the file.
     * @throws IOException if an exception occurs while the file is being read.
     */
    @Override
    public String read() throws IOException {
        File file = new File(this.path);
        file.createNewFile();

        BufferedReader reader = new BufferedReader(new java.io.FileReader(path));
        StringBuilder builder = new StringBuilder();
        String line = null;

        while((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();
        return builder.toString();
    }
}
