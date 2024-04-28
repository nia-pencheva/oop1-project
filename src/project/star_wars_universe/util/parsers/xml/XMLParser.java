package project.star_wars_universe.util.parsers.xml;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.data.AppData;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.util.ParsingFailureException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class XMLParser implements Parser<String, AppData> {
    @Override
    public AppData parse(String content) throws ParsingFailureException {
        try {
            Document document = convertStringToDOM(content);
            List<Jedi> jedi = (new JediXMLParser()).parse(document.getElementsByTagName("jedi-list").item(0).getChildNodes());
            List<Planet> planets = (new PlanetsXMLParser(jedi)).parse(document.getElementsByTagName("planets-list").item(0).getChildNodes());

            return new AppData(jedi, planets);
        }
        catch(IOException | SAXException | ParserConfigurationException ex) {
            throw new ParsingFailureException(ex);
        }
    }

    private Document convertStringToDOM(String content) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(content)));
        document.getDocumentElement().normalize();
        return document;
    }
}
