package project.star_wars_universe.resource.file;

import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.contracts.util.Serializer;
import project.star_wars_universe.data.AppData;
import project.star_wars_universe.util.parsers.xml.XMLParser;
import project.star_wars_universe.util.serializers.xml.XMLSerializer;

public class XMLFile extends File {
    public XMLFile(String path) {
        super(path);
    }

    @Override
    public Parser<String, AppData> getParser() {
        return new XMLParser();
    }

    @Override
    public Serializer<AppData, String> getSerializer() {
        return new XMLSerializer();
    }
}
