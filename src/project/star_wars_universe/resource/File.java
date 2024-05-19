package project.star_wars_universe.resource;

import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.input_output.FileReader;
import project.star_wars_universe.input_output.FileWriter;

import java.io.IOException;

/**
 * Represents a file. It is an abstract class, therefore it cannot be instantiated.
 * It is supposed to be extended by classes representing files of concrete formats
 * (.xml, .txt, etc.). It contains a path to an actual file, as well as
 * a {@link Parser} and a {@link Serializer}.
 */
public abstract class File {
    /**
     * The path of the file that the {@link File} instance represents.
     */
    private String path;
    /**
     * A {@link Parser} instance used for transforming the file data
     * from its specific format to a format that the application can work with.
     */
    private Parser parser;
    /**
     * A {@link Serializer} instance used for transforming the application data
     * to the specific format of the file.
     */
    private Serializer serializer;

    /**
     * Initializes an instance of the {@link File} class with the path of the file it should represent
     * and a parser and a serializer corresponding to the file's data format.
     * @param path the path of the file that the {@link File} instance should it should represent
     * @param parser an appropriate {@link Parser} capable of parsing the data in the file.
     * @param serializer an appropriate {@link Serializer} capable of serializing the application data to the file's data format.
     */
    public File(String path, Parser parser, Serializer serializer) {
        this.path = path;
        this.parser = parser;
        this.serializer = serializer;
    }

    /**
     * Gets all data from the file represented by the {@link File} instance.
     * A {@link FileReader} is used to read the data.
     * @return the data fetched from the file.
     * @throws IOException f an exception occurs while the file is being read.
     */
    public String getData() throws IOException {
        return (new FileReader(path)).read();
    }

    /**
     * Saves data to the file represented by the {@link File} instance.
     * A {@link FileWriter} is used to write the data.
     * @param data the data that should be saved to the file.
     * @throws IOException if an exception occurs while the file is being read.
     */
    public void saveData(String data) throws IOException {
        (new FileWriter(path)).write(data);
    }

    /**
     * Gets the path of the file that the {@link File} instance represents.
     * @return the path of the file that the {@link File} instance represents.
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets the file's parser.
     * @return the file's parser.
     */
    public Parser<String> getParser() {
        return parser;
    }

    /**
     * Gets the file's serializer.
     * @return the file's serializer.
     */
    public Serializer<String> getSerializer() {
        return serializer;
    }
}
