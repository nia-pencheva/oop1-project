package project.star_wars_universe.resource;

import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.util.parsers.XMLParser;
import project.star_wars_universe.util.serializers.XMLSerializer;

public class XMLFile extends File {
    public XMLFile(String path) {
        super(path);
    }

    @Override
    public Parser<String> getParser() {
        return new XMLParser();
    }

    @Override
    public Serializer<String> getSerializer() {
        return new XMLSerializer();
    }
}
