package project.star_wars_universe.contracts.resource;

import java.io.FileNotFoundException;

public interface Resource<T> {
    T getData() throws Exception;
    void update();
}
