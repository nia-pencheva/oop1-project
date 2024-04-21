package project.star_wars_universe.input_output.file;

import project.star_wars_universe.contracts.input_output.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

public class FileReader implements Reader {
    private String path;

    public FileReader(String path) {
        this.path = path;
    }

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
