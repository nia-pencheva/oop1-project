package project.star_wars_universe.resource;

import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.input_output.FileReader;
import project.star_wars_universe.input_output.FileWriter;

import java.io.IOException;

/**
 * Represents a file. It contains a path to an actual file, as well as
 * a {@link Parser} and a {@link Serializer}.
 */
public abstract class File {
    private String path;
    private Parser parser;
    private Serializer serializer;

    public File(String path) {
        this.path = path;
    }

    public String getData() throws IOException {
        return (new FileReader(path)).read();
    }

    public void saveData(String data) throws IOException {
        (new FileWriter(path)).write(data);
    }

    public String getPath() {
        return path;
    }

    public abstract Parser<String> getParser();

    public abstract Serializer<String> getSerializer();
}
