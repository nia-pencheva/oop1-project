package project.star_wars_universe.contracts.resource;

import java.io.FileNotFoundException;

public interface Resource {
    String getData() throws Exception;
    void saveData(String data);
}
