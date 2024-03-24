package project.star_wars_universe.util.parsers.xml;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import project.star_wars_universe.contracts.util.Parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XMLDataParser implements Parser<Map<String, Set>, String> {
    private JediXMLParser jediXMLParser = new JediXMLParser();
    private PlanetsXMLParser planetsXMLParser = new PlanetsXMLParser();

    @Override
    public Map<String, Set> parse(String content) throws IOException, SAXException, ParserConfigurationException {
        Map<String, Set> parsedData = new HashMap<>();
        Document document = convertStringToDOM(content);
        parsedData.put("jediList", jediXMLParser.parse(document.getElementsByTagName("jedi-list").item(0).getChildNodes()));
        parsedData.put("planetsList", planetsXMLParser.parse(document.getElementsByTagName("planets-list").item(0).getChildNodes()));
        return parsedData;
    }

    private Document convertStringToDOM(String content) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(content)));
        document.getDocumentElement().normalize();
        return document;
    }
}
