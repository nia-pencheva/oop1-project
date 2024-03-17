package project.star_wars_universe.resource;

import project.star_wars_universe.contracts.resource.Resource;
import project.star_wars_universe.input_output.file.FileReader;


import java.io.IOException;

public class File implements Resource {
    private String path;

    public File(String path) {
        this.path = path;
    }

    @Override
    public String getData() throws IOException {
        return (new FileReader(path)).read();
    }

    @Override
    public void update() {

    }
}
