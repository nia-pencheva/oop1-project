package project.star_wars_universe.util.parsers.xml;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.data.AppData;
import project.star_wars_universe.entities.jedi.Jedi;
import project.star_wars_universe.entities.planets.Planet;
import project.star_wars_universe.exceptions.jedi.InvalidAgeException;
import project.star_wars_universe.exceptions.jedi.InvalidPowerException;
import project.star_wars_universe.exceptions.jedi.InvalidRankException;
import project.star_wars_universe.exceptions.jedi.InvalidSaberColorException;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

public class XMLParser implements Parser<String, AppData> {
    @Override
    public AppData parse(String content) throws IOException, SAXException, ParserConfigurationException, InvalidAgeException, InvalidRankException, InvalidSaberColorException, InvalidPowerException, JediExistsOnThisPlanetException {
        Document document = convertStringToDOM(content);
        Set<Jedi> jedi = (new JediXMLParser()).parse(document.getElementsByTagName("jedi-list").item(0).getChildNodes());
        Set<Planet> planets = (new PlanetsXMLParser()).parse(document.getElementsByTagName("planets-list").item(0).getChildNodes());

        return new AppData(jedi, planets);
    }

    private Document convertStringToDOM(String content) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(content)));
        document.getDocumentElement().normalize();
        return document;
    }
}
