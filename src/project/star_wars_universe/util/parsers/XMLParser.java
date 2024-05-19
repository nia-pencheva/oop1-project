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

/**
 * Parses XML {@code String} data into an {@link AppData} object, which
 * can be utilized by the application.
 */
public class XMLParser implements Parser<String> {
    /**
     * Parses an XML {@code String} into a {@link AppData} object. The content is first converted
     * to a {@link Document} object using the {@link XMLParser#convertStringToDOM(String)} method.
     * The information about the jedi (contained in a {@code <jedi-list>} node) is converted to a {@code List} using the {@link XMLParser#parseJedi(NodeList)} method.
     * The information about the planets (contained in a {@code <planets-list>} node) is converted to a {@code List} using the {@link XMLParser#parsePlanets(NodeList, List)}} method.
     * Using the two lists, an {@link AppData} is created and returned.
     * If an exception occurs during the parsing of the data ({@link IOException}, {@link SAXException}, {@link ParserConfigurationException})
     * the exception is caught and passed to a {@link ParsingFailureException}, from which the specific
     * exception can later be extracted using the {@link ParsingFailureException#getException()} method.
     * @param content the XML {@code String} that should be parsed.
     * @return an {@link AppData} object containing the parsed application data.
     * @throws ParsingFailureException if an exception occurs during the parsing process.
     */
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

    /**
     * Converts an XML {@code String} to a {@link Document} object.
     * @param content the XML string which should be converted to a document.
     * @return a {@link Document} object derived from the provided {@code String} content.
     * @throws IOException if any IO error occurs.
     * @throws SAXException if any parse error occurs.
     * @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the configuration requested.
     */
    private Document convertStringToDOM(String content) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        StringReader reader = new StringReader(content);
        Document document = builder.parse(new InputSource(reader));
        reader.close();
        document.getDocumentElement().normalize();
        return document;
    }

    /**
     * Converts a {@link NodeList} to a {@code Set} of {@link Jedi}.
     *  The {@link NodeList} containing the {@code <jedi-list>} node's child elements is iterated through.
     *  The data for each jedi is extracted from the child nodes of each {@code <jedi>} node:
     * <ul>
     *     <li>{@code <name>} - the jedi's name</li>
     *     <li>{@code <rank>} - the jedi's rank</li>
     *     <li>{@code <age>} - the jedi's age</li>
     *     <li>{@code <saber-color> - the jedi's saber color}</li>
     *     <li>{@code <power> - the jedi's power}</li>
     * </ul>
     * These values are converted from {@code String} to their correct data types.
     * Then it is checked whether the current jedi has already been added to the {@code jediList}
     * variable and if not it is added to it. If the jedi has already been added to the {@code jediList}
     * ({@link JediAlreadyExistsException}) or any of the values provided for the fields of the jedi
     * are invalid ({@link project.star_wars_universe.exceptions.planets.InvalidNameException}, {@link InvalidRankException},
     * {@link InvalidAgeException}, {@link InvalidSaberColorException}, {@link InvalidPowerException}),
     * the exception is passed to a {@link ParsingFailureException} instance, from which the specific
     * exception can later be extracted using the {@link ParsingFailureException#getException()} method.
     * The final result is contained in the {@code jediList} variable, which is returned.
     * @param content a {@link NodeList} containing the {@code <jedi-list>} node's child elements.
     * @return a {@code List} of {@link Jedi}.
     * @throws ParsingFailureException if an exception occurs during the parsing process.
     */
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

    /**
     * Converts a {@link NodeList} to a {@code Set} of {@link Planet}s.
     * First, the {@code jediList} that is passed as an argument to this method is converted
     * to a {@code jedi Map}, where the keys are the names of the jedi and the values are the {@link Jedi} objects.
     * The {@link NodeList} containing the {@code <planets-list>} node's child elements is iterated through.
     * The data for each planet is extracted from the child nodes of each {@code <planet>} node:
     * <ul>
     *     <li>{@code <name>} - the planet's name. A new {@link Planet} class with that name is instantiated.</li>
     *     <li>
     *         {@code <jedi-population>} - the planet's jedi population.
     *         It's {@code <jedi>} child nodes contain the name of each jedi that populates the planet.
     *         For each name it is checked whether it exists in the {@code jedi} map. If it does,
     *         it gets added to the newly created planet's jedi population. The jedi is then removed form the
     *         {@code jedi} map.
     *     </li>
     * </ul>
     * Finally, the new planet is added to the {@code planetsList} variable. When all planets have been iterated
     * through, the {@code planetsList} is returned. If any of the {@code <planet> -> <jedi-population> -> <name>}
     * nodes contains a name which doesn't correspond to an existing jedi ({@link JediDoesNotExistException}), a jedi
     * with that name already exists on a given planet ({@link project.star_wars_universe.exceptions.planets.JediDoesNotExistOnThisPlanetException})
     * or the planet name is invalid ({@link project.star_wars_universe.exceptions.planets.InvalidNameException}), the exception
     * is passed to a {@link ParsingFailureException} instance, from which the specific exception can later be extracted
     * using the {@link ParsingFailureException#getException()} method.
     * @param content a {@link NodeList} containing the {@code <planets-list>} node's child elements.
     * @param jediList the {@code List} of {@link Jedi} that have been fetched from the XML.
     * @return a {@code List} of {@link Planet}s.
     * @throws ParsingFailureException if an exception occurs during the parsing process.
     */
    public List<Planet> parsePlanets(NodeList content, List<Jedi> jediList) throws ParsingFailureException {
        Map<String, Jedi> jedi = new HashMap<>();

        for(Jedi item : jediList) {
            jedi.put(item.getName(), item);
        }

        try {
            List<Planet> planetsList = new ArrayList<>();
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

                        planetsList.add(planet);
                    }
                }
            }

            return planetsList;
        }
        catch(project.star_wars_universe.exceptions.planets.InvalidNameException | JediExistsOnThisPlanetException | JediDoesNotExistException ex) {
            throw new ParsingFailureException(ex);
        }
    }
}
