package project.star_wars_universe.resource;

import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.data.AppData;
import project.star_wars_universe.input_output.file.FileReader;
import project.star_wars_universe.input_output.file.FileWriter;

import java.io.IOException;

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

    public abstract Parser<String, AppData> getParser();

    public abstract Serializer<AppData, String> getSerializer();
}
