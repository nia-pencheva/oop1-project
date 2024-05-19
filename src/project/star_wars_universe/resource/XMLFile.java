package project.star_wars_universe.resource;

import project.star_wars_universe.util.parsers.XMLParser;
import project.star_wars_universe.util.serializers.XMLSerializer;

/**
 * Represents an XML File.
 */
public class XMLFile extends File {
    /**
     * Initializes the {@link XMLFile} instance with a specified file name,
     * an {@link XMLParser} and an {@link XMLSerializer}.
     * @param path
     */
    public XMLFile(String path) {
        super(path, new XMLParser(), new XMLSerializer());
    }
}
