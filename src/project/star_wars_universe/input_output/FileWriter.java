package project.star_wars_universe.input_output;

import project.star_wars_universe.contracts.input_output.Writer;

import java.io.IOException;

public class FileWriter implements Writer {
    private String path;

    public FileWriter(String path) {
        this.path = path;
    }

    @Override
    public void write(String content) throws IOException {
        java.io.FileWriter wr = new java.io.FileWriter(path);
        wr.write(content);
        wr.flush();
        wr.close();
    }
}
