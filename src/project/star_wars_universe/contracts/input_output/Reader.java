package project.star_wars_universe.contracts.input_output;

public interface Reader<T> {
    T read() throws Exception;
}
