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
     * @param path the path of the XML file that the {@link XMLFile} instance should represent.
     */
    public XMLFile(String path) {
        super(path, new XMLParser(), new XMLSerializer());
    }
}
