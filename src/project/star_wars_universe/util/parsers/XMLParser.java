package project.star_wars_universe.util.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import project.star_wars_universe.contracts.util.Parser;
import project.star_wars_universe.data.AppData;
import project.star_wars_universe.exceptions.jedi.*;
import project.star_wars_universe.exceptions.planets.JediExistsOnThisPlanetException;
import project.star_wars_universe.models.jedi.Jedi;
import project.star_wars_universe.models.jedi.enums.Rank;
import project.star_wars_universe.models.jedi.enums.SaberColor;
import project.star_wars_universe.models.planets.Planet;
import project.star_wars_universe.exceptions.util.ParsingFailureException;
import project.star_wars_universe.util.parsers.base_type.DoubleParser;
import project.star_wars_universe.util.parsers.base_type.IntegerParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLParser implements Parser<String> {
    @Override
    public AppData parse(String content) throws ParsingFailureException {
        try {
            Document document = convertStringToDOM(content);
            List<Jedi> jedi = parseJedi(document.getElementsByTagName("jedi-list").item(0).getChildNodes());
            List<Planet> planets = parsePlanets(document.getElementsByTagName("planets-list").item(0).getChildNodes(), jedi);

            return new AppData(jedi, planets);
        }
        catch(IOException | SAXException | ParserConfigurationException ex) {
            throw new ParsingFailureException(ex);
        }
    }

    private Document convertStringToDOM(String content) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        StringReader reader = new StringReader(content);
        Document document = builder.parse(new InputSource(reader));
        reader.close();
        document.getDocumentElement().normalize();
        return document;
    }

    public List<Jedi> parseJedi(NodeList content) throws ParsingFailureException {
        try {
            List<Jedi> jediList = new ArrayList<>();
            Node currentNode = null;
            Jedi newJedi;
            String name;
            Rank rank;
            int age;
            SaberColor saberColor;
            double power;

            for(int i = 0; i < content.getLength(); i++) {
                currentNode = content.item(i);
                if(currentNode.getNodeName().equals("jedi")) {
                    if(currentNode instanceof Element) {
                        Element el = (Element) currentNode;
                        name = el.getElementsByTagName("name").item(0).getTextContent();
                        rank = Rank.getValue(el.getElementsByTagName("rank").item(0).getTextContent());
                        age = IntegerParser.parse(el.getElementsByTagName("age").item(0).getTextContent());
                        saberColor = SaberColor.getValue(el.getElementsByTagName("saber-color").item(0).getTextContent());
                        power = DoubleParser.parse(el.getElementsByTagName("power").item(0).getTextContent());
                        newJedi = new Jedi(name, rank, age, saberColor, power);

                        if(jediList.contains(newJedi)) {
                            throw new JediAlreadyExistsException();
                        }

                        jediList.add(newJedi);
                    }
                }
            }

            return jediList;
        }
        catch(InvalidNameException | InvalidRankException | InvalidAgeException | InvalidSaberColorException | InvalidPowerException | JediAlreadyExistsException ex) {
            throw new ParsingFailureException(ex);
        }
    }

    public List<Planet> parsePlanets(NodeList content, List<Jedi> jediList) throws ParsingFailureException {
        Map<String, Jedi> jedi = new HashMap<>();

        for(Jedi item : jediList) {
            jedi.put(item.getName(), item);
        }

        try {
            List<Planet> planetsSet = new ArrayList<>();
            Node currentNode = null;
            Element el = null;
            String jediName;

            for(int i = 0; i < content.getLength(); i++) {
                currentNode = content.item(i);

                if(currentNode.getNodeName().equals("planet")) {
                    if(currentNode instanceof Element) {
                        el = (Element) currentNode;
                        Planet planet = new Planet(el.getElementsByTagName("name").item(0).getTextContent());
                        NodeList jediPopulation = el.getElementsByTagName("jedi-population").item(0).getChildNodes();

                        for(int j = 0; j < jediPopulation.getLength(); j++) {
                            currentNode = jediPopulation.item(j);

                            if(currentNode.getNodeName().equals("jedi")) {
                                jediName = currentNode.getTextContent();
                                if(jedi.containsKey(jediName)) {
                                    planet.addJedi(jedi.get(jediName));
                                    jedi.remove(currentNode.getTextContent());
                                }
                                else {
                                    throw new JediDoesNotExistException();
                                }
                            }
                        }

                        planetsSet.add(planet);
                    }
                }
            }

            return planetsSet;
        }
        catch(project.star_wars_universe.exceptions.planets.InvalidNameException | JediExistsOnThisPlanetException | JediDoesNotExistException ex) {
            throw new ParsingFailureException(ex);
        }
    }
}
